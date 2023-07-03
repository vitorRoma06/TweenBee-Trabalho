package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TwinBeeJogo extends Game {
	SpriteBatch batch;// objeto de desenho de imagem
	BitmapFont font;

	public void create() {
		batch = new SpriteBatch();
		// A fonte usada será Comic Sans
		font = new BitmapFont(Gdx.files.internal("assets/consolas.fnt"));
		this.setScreen(new GameScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}