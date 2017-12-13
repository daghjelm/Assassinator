package com.mygdx.game.GameObjects;

import com.mygdx.game.MainGame;
import com.mygdx.game.screens.MainGameScreen;

import java.util.ArrayList;

/**
 * Created by joarr on 2017-05-16.
 */
public class Collisions {

    public static final int COLLISSION_DETECTION_RADIUS = 4;
    public static final int POST_COLLISION_DISPLACEMENT = 6;

    private ArrayList<SolidGameObject> solidObjects;
    private ArrayList<Bullet> bullets;

    public Collisions(ArrayList<Bullet> bullets, ArrayList<SolidGameObject> solidGameObjects) {
        this.bullets = bullets;
        this.solidObjects = solidGameObjects;
    }

    public void update() {

        for (Bullet bullet : bullets) {
            for (SolidGameObject object : solidObjects) {

                float bulletX = bullet.getX();
                float bulletY = bullet.getY();

                float topLim = object.getY() + object.getHeight() + COLLISSION_DETECTION_RADIUS;
                float bottomLim = object.getY() - COLLISSION_DETECTION_RADIUS;

                float rightLim = object.getX() + object.getWidth() + COLLISSION_DETECTION_RADIUS;
                float leftLim = object.getX() - COLLISSION_DETECTION_RADIUS;

                if(bulletX <= rightLim && bulletX >= leftLim && bulletY >= bottomLim && bulletY <= topLim) {
                    // calculate distances from each wall
                    float rightDiff = Math.abs(bulletX - rightLim);
                    float leftDiff = Math.abs(bulletX - leftLim);
                    float topDiff = Math.abs(topLim - bulletY);
                    float bottomDiff = Math.abs(bottomLim - bulletY);

                    // find smallest distance
                    float minX = rightDiff < leftDiff ? rightDiff : leftDiff;
                    float minY = topDiff < bottomDiff ? topDiff : bottomDiff;

                    if (minX < minY) {
                        float newX = rightDiff < leftDiff ? rightLim + POST_COLLISION_DISPLACEMENT : leftLim - POST_COLLISION_DISPLACEMENT;
                        bullet.collisionEvent(false, newX, bullet.getY());
                    } else {
                        float newY = topDiff < bottomDiff ? topLim + POST_COLLISION_DISPLACEMENT : bottomLim - POST_COLLISION_DISPLACEMENT;
                        bullet.collisionEvent(true, bullet.getX(), newY);
                    }

                    // notify object it was struck
                    object.collisionEvent();
                }
            }
        }
    }
}
