package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MainGame;

/**
 * Main class for starting the game.
 */
public class DesktopLauncher {

	/**
	 * Main method to initialize game execution. Sets up
	 * game environment and window.
	 * @param args
	 */
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.width = MainGame.WIDTH;
		config.height = MainGame.HEIGHT;
		config.resizable = false;
		new LwjglApplication(new MainGame(), config);
	}
}
