package com.mygdx.game.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Interface for game objects which can be hit by bullets
 */
public interface SolidGameObject {

    /**
     * Call this method to notify this solid object has been hit
     * by a bullet. This method should contain this objects response
     * to being shot (breaking, dying, losing health etc.)
     */
    void collisionEvent();

    /**
     * Return x coordinate of botto, left corner of hitbox for this obj
     */
    float getX();

    /**
     * Return y coordinate of bottom left corner of hitbox for this obj
     */
    float getY();

    /**
     * Return height of hitbox for this obj
     */
    float getHeight();

    /**
     * Return width of hitbox for this obj
     */
    float getWidth();

    /**
     * Draw object to game screen
     * @param batch sprite batch to use for drawing
     */
    void render(SpriteBatch batch);
}
