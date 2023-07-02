package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ApplicationAdapter implements Screen {

    final TwinBeeJogo game;

    Texture background;
    Texture nave;
    Texture tiro1;
    Texture alien1;

    public SpriteBatch batch;
    Random rand = new Random();
    Background bk;
    Movel player;
    Projeteis tiro;
    Movel alien;

    int larg, alt;
    float tempo = 0;

    public GameScreen(TwinBeeJogo game) {
        this.game = game;
        batch = new SpriteBatch();
        background = new Texture("background.png");
        bk = new Background(background);
        nave = new Texture("nave.png");
        tiro1 = new Texture("tiro1.png");
        alien1 = new Texture("alien1.png");
        tiro = new Projeteis(tiro1, batch);
        player = new Player(nave, tiro1, tiro);
        alien = new Alien(alien1, 0, 1, 500);
    }

    @Override
    public void render(float delta) {
        batch.begin();
		bk.draw(batch);
		bk.run();
		tiro.draw(batch);
        alien.draw(batch);
		player.draw(batch);
        alien.posicaoIgual(tiro.getPosX(), tiro.getPosY());
		batch.end();
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
        batch.dispose();
		background.dispose();
    }

    @Override
    public void show() {
    }

}
