package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGameScreen extends ApplicationAdapter implements Screen {

    final TwinBeeJogo game;

    private Texture background;
    private Texture nave;
    private Texture tiro1;
    private Texture alien1;

    public SpriteBatch batch;
    private Background bk;
    private Movel player;
    private Projeteis tiro;
    private Movel alien;

    int larg, alt;
    float tempo = 0;
    float scale = 2; 

    public MainGameScreen(TwinBeeJogo game) {
        this.game = game;
        batch = new SpriteBatch();
        background = new Texture("background.png");
        bk = new Background(background);
        nave = new Texture("nave.png");
        tiro1 = new Texture("tiro22.png");
        alien1 = new Texture("alien1.png");
        tiro = new Projeteis(tiro1, batch, scale);
        player = new Player(nave, tiro1, tiro);
        alien = new Alien(alien1, 0, Gdx.graphics.getWidth());

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
