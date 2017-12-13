package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.screens.MainMenuScreen;

/**
 * Central class responsible for running the game.
 * TODO write more here
 */
public class MainGame extends Game {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 700;

	public SpriteBatch batch;
	public ShapeRenderer shapes;

	public GlyphLayout layout;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shapes = new ShapeRenderer();
		layout = new GlyphLayout();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapes.dispose();
	}
}
