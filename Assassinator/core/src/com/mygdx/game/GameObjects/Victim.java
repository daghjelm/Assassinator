package com.mygdx.game.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by joarr on 2017-05-18.
 */
public class Victim implements SolidGameObject {

    private boolean dead;
    private float x;
    private float y;
    private float width;
    private float height;

    private Texture texture;

    public Victim(float x, float y, float width, float height) {
        dead = false;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        texture = new Texture("victim.png");
    }

    public boolean isDead() {
        return dead;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void collisionEvent() {
        dead = true;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }
}
