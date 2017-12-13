package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.MainGame;

/**
 * Created by user on 2017-05-17.
 */
public class GameOverScreen implements Screen {

    private final int EXIT_BUTTON_WIDTH = 200;
    private final int EXIT_BUTTON_HEIGHT = 50;
    private final int PLAY_BUTTON_WIDTH = 200;
    private final int PLAY_BUTTON_HEIGHT = 50;
    private final int EXIT_BUTTON_Y = 50;
    private final int PLAY_BUTTON_Y = 130;
    private final float FONT_WIDTH = 300;

    private BitmapFont font;
    private String text;

    private MainGame game;

    private Texture playButtonActive;
    private Texture playButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;


    public GameOverScreen(MainGame game){
        this.game = game;
        playButtonActive = new Texture("play_button_active.png");
        playButtonInactive = new Texture("play_button_inactive.png");
        exitButtonActive = new Texture("exit_button_active.png");
        exitButtonInactive = new Texture("exit_button_inactive.png");
    }


    @Override
    public void show() {
        font = new BitmapFont(Gdx.files.internal("newfont.fnt"));
    }

    @SuppressWarnings("Duplicates")
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        int x1 = MainGame.WIDTH/2 - EXIT_BUTTON_WIDTH/2;
        if(Gdx.input.getX() < x1 + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x1 &&
                MainGame.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT &&
                MainGame.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y) {
            game.batch.draw(exitButtonActive, x1,
                    EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if(Gdx.input.isButtonPressed(0)) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButtonInactive, x1,
                    EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }
        int x2 = MainGame.WIDTH/2 - PLAY_BUTTON_WIDTH/2;
        if(Gdx.input.getX() < x2 + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x2 &&
                MainGame.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT &&
                MainGame.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y) {
            game.batch.draw(playButtonActive, x2,
                    PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if(Gdx.input.isButtonPressed(0)) {
                this.dispose();
                game.setScreen(new MainGameScreen(game, 0));
            }
        } else {
            game.batch.draw(playButtonInactive, x2,
                    PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }


        font.getData().setScale(3, 3);
        text = "GAME OVER";
        game.layout.setText(font, text);
        font.setColor(Color.RED);
        font.draw(game.batch, text, game.WIDTH/2 - game.layout.width/2, game.HEIGHT - game.layout.height - 200);

        game.batch.end();

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

    }
}
