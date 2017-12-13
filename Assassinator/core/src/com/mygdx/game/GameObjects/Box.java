package com.mygdx.game.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MainGame;

/**
 * Created by user on 2017-05-12.
 */
public class Box implements SolidGameObject {

    private final boolean destructible;
    private final float xPos;
    private final float yPos;
    private final float width;
    private final float height;

    private Texture texture;

    private boolean destroyed;

    /**
     * Make a new Box object
     * @param xPos x coordinate of top left corner
     * @param yPos y coordinate of top left corner
     * @param width with of box (in pixels)
     * @param height height of box (in pixels)
     * @param destructible true if the box should be destroyed when shot
     */
    public Box(float xPos, float yPos, float width, float height, boolean destructible) {
        this.destructible = destructible;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        texture = new Texture("box.png");
    }

    @Override
    public void collisionEvent() {
        destroyed = destructible; // kill the box if it is mortal
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, xPos, yPos);
    }

    @Override
    public float getX() {
        return xPos;
    }

    @Override
    public float getY() {
        return yPos;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public float getWidth() {
        return width;
    }
}
