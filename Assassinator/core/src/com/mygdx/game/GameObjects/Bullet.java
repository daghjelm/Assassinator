package com.mygdx.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Bullet objects used in Assassinator. Holds game logic,
 * update function and render function for bullets.
 */
public class Bullet {

    private static final int DEFAULT_Y = 100;

    private float startAngle = -90;

    //The bullet's speed
    private float xSpeed = 5f;
    private float ySpeed = 5f;

    //private float timeStep = 1f/60f;

    public boolean remove = false;

    private float norm = 100f;

    private float xDir, yDir;


    private float x;
    private float y;

    /**
     * Initialize a new bullet object
     * @param x Initial x axis position
     * @param xDir TODO param description
     * @param yDir TODO param desription
     */
    public Bullet(float x, float xDir, float yDir) {
        this.x = x;
        this.y = DEFAULT_Y;

        this.xDir = calcX(xDir - x, yDir - y);
        this.yDir = calcY(xDir - x, yDir - y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    private void setX(float x) {
        this.x = x;
    }

    private void setY(float y) {
        this.y = y;
    }

    //Function for calculating the normalised x
    private float calcX(float x, float y) {
        float z = (float)Math.sqrt(x*x + y*y);

        float k = z/norm;

        float z2 = z/k;

        return (z2 * x)/z;
    }

    //Function for calculating the normalised y
    private float calcY(float x, float y) {
        float z = (float)Math.sqrt(x*x + y*y);

        float k = z/norm;

        float z2 = z/k;

        return (z2 * y)/z;
    }

    /**
     * Uptade state of this bullet by changing
     * position according to bullet velocity and
     * time difference since last update.
     * @param deltaTime The time since last update
     *                  measured in TODO Unit here plz
     */
    public void update(float deltaTime) {

        y += ySpeed * yDir * deltaTime;

        x += xSpeed * xDir * deltaTime;

        if(Gdx.graphics.getHeight() < y || y < 0) {
            remove = true;
        }

        if(Gdx.graphics.getWidth() - 8 < x || x < 8) {
            xSpeed = - xSpeed;
        }
    }

    /**
     * Change direction as the bullet hits a wall or object.
     *
     * @param side True if a vertical surface was hit, false
     *             if a horizontal surface was hit. As per this
     *             illustration:
     *
     *         |        true
     *         |       * * * *
     *         |       *     *
     *         | false *     * false
     *         |       *     *
     *         |       * * * *
     *         |        true
     *
     * @param newX New x coordinate, outside of hitbox
     * @param  newY New y coordinate, outside of hitbox
     */
    public void collisionEvent(boolean side, float newX, float newY) {

        setX(newX);
        setY(newY);

        if (side) {
            ySpeed = -ySpeed;
        } else {
            xSpeed =-xSpeed;
        }
    }

    /**
     * Draw this bullet onto the game screen
     * @param shapes TODO parameter description
     */
    public void render(ShapeRenderer shapes) {
        shapes.setColor(Color.WHITE);
        shapes.circle(x, y, 5);
    }
}

