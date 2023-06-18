package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.badlogic.gdx.Gdx;

public class TwinBeeJogo extends ApplicationAdapter {
	public SpriteBatch batch;
	Texture background;
	Random rand = new Random();
	int larg, alt;
	Background bk;
	float tempo = 0;
	Movel player;
	Texture nave;
	Texture tiro1;
	Projeteis tiro;


	@Override
	public void create() {
		
		batch = new SpriteBatch();
		background = new Texture("background.png");
		bk = new Background(background);
		nave = new Texture("nave.png");
		tiro1 = new Texture("tiro1.png");
		tiro = new Projeteis(tiro1, batch);
		player = new Player(nave, tiro1, tiro);


	}

	@Override
	public void render() {
		
		batch.begin();
		bk.draw(batch);
		bk.run();
		tiro.draw(batch);
		player.draw(batch);
		batch.end();
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		background.dispose();
	}
}