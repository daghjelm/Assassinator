package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.GameObjects.*;
import com.mygdx.game.MainGame;
import java.util.ArrayList;
import java.util.Random;

/**
 * Screen class for game screen.
 */
public class MainGameScreen implements Screen {


    public final static int SPEED = 300;
    private static final int SUIT_MAN_HEIGHT = 100;
    private static final int SUIT_MAN_WIDTH = 100;
    private static final float SHOOT_WAIT_TIME = 0.5f;
    private float shootTimer;
    private int maxBullets = 3;

    private Texture img;
    private Texture backgroundTexture;

    private float x;
    private float y;
    private MainGame game;
    private int shotCounter = 0;

    private static ArrayList<Bullet> bullets;
    private Victim victim;
    private static ArrayList<SolidGameObject> solidGameObjects;
    private Collisions collisions;

    private Random random;

    private static final int XGRID = 3;
    private static final int YGRID = 3;

    private int boxSize = 60;

    private int score;

    //Font stuff
    private BitmapFont font;
    private String text;
    private String scoreText;

    public MainGameScreen(MainGame game, int score) {
        this.game = game;
        x = game.WIDTH/2 - SUIT_MAN_WIDTH/2;
        y = 0;
        random = new Random();
        bullets = new ArrayList<Bullet>();
        victim = new Victim(200, 600, 60, 60);
        solidGameObjects = new ArrayList<SolidGameObject>();
        solidGameObjects.add(victim);
        this.score = score;

        int[][][] coordArray = randomizeBoxes(XGRID, YGRID);
        for(int i = 0; i < YGRID; i++) {
            for(int j = 0; j < XGRID; j++) {
                solidGameObjects.add(new Box(coordArray[i][j][0], coordArray[i][j][1], boxSize, boxSize, false));
            }
        }
        //solidGameObjects.add(new Box(0, 120, 50, 50, false));

        collisions = new Collisions(bullets, solidGameObjects);
    }

    @Override
    public void show() {
        img = new Texture("suit-man.png");
        font = new BitmapFont(Gdx.files.internal("newfont.fnt"));
        backgroundTexture = new Texture("bakgrund.png");

    }

    //Simple method to render background
    private void renderBackground() {
        game.batch.draw(backgroundTexture, 0, 0, game.WIDTH, game.HEIGHT);
    }

    /**
     * Method for generating box positions
     */
    private int[][][] randomizeBoxes(int XGRID, int YGRID) {
        int[][][] array = new int[YGRID][XGRID][2];
        for(int i = 0; i < YGRID; i++) {
            for(int j = 0; j < XGRID; j++) {
                array[i][j][0] = j*(480/XGRID) + random.nextInt((480/XGRID) - boxSize);
                array[i][j][1] = 120 + i*(500/YGRID) + random.nextInt((450/YGRID) - boxSize);
            }
        }
        return array;
    }

    @Override
    public void render(float delta) {

        //Shooting
        shootTimer += delta;
        if(Gdx.input.isButtonPressed(0) && shootTimer >= SHOOT_WAIT_TIME) {
            shootTimer = 0;

            if(shotCounter < maxBullets) {
                bullets.add(new Bullet(x + 50, Gdx.input.getX(), game.HEIGHT - Gdx.input.getY()));
                shotCounter++;
            }
        }

        //Update bullets
        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
        for(Bullet bullet : bullets) {
            bullet.update(delta);
            if(bullet.remove)
                bulletsToRemove.add(bullet);
        }
        bullets.removeAll(bulletsToRemove);

        //If shotCounter is more than 3 and we all bullets are out of bounds, go back to main menu.
        if(shotCounter >= maxBullets && bullets.size() == 0) {
            game.setScreen(new GameOverScreen(game));
        }

        // Collision detection
        collisions.update();

        //End game if victim died
        if (victim.isDead()) {
            game.setScreen(new MainGameScreen(game, score + 10));
        }

        //Movements
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            x -= SPEED * Gdx.graphics.getDeltaTime();

            if(x < 0)
                x = 0;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            x += SPEED * Gdx.graphics.getDeltaTime();

            if(x > game.WIDTH - SUIT_MAN_WIDTH)
                x = game.WIDTH - SUIT_MAN_WIDTH;

        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        //Draw background
        game.batch.disableBlending();
        renderBackground();
        game.batch.enableBlending();

        //Draw suit man
        game.batch.draw(img, x, y, SUIT_MAN_WIDTH, SUIT_MAN_HEIGHT);

        //Show shot-counter
        text = "Bullets left: " + (maxBullets - shotCounter);

        game.layout.setText(font, text);
        font.draw(game.batch, text, game.WIDTH - game.layout.width - 5, game.HEIGHT - game.layout.height - 4);

        //Show score-counter
        scoreText = "Score: " + score;

        game.layout.setText(font, scoreText);
        font.draw(game.batch, scoreText, 10, game.HEIGHT - game.layout.height - 4);

        for (SolidGameObject object : solidGameObjects) {
            object.render(game.batch);
        }

        game.batch.end();

        game.shapes.begin(ShapeRenderer.ShapeType.Filled);

        for(Bullet bullet : bullets) {
            bullet.render(game.shapes);
        }

        game.shapes.end();
    }

    public static ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public static ArrayList<SolidGameObject> getSolidGameObjects() {
        return solidGameObjects;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        img.dispose();
    }
}
