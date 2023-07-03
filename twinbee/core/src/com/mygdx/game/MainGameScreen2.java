package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Random;
import com.badlogic.gdx.utils.TimeUtils;

public class MainGameScreen2 extends ApplicationAdapter implements Screen{
    final TwinBeeJogo game;

    private Texture background;
    private Texture nave;
    private Texture tiro1;
    private Texture alien1;
    private Texture alien2;
    private Texture alien3;

    public SpriteBatch batch;
    private Background bk;
    private Movel player;
    private Projeteis tiro;
    private Movel alien;
    Alien aliens[] = new Alien[10];
    private Movel boss;

    Random rand = new Random();
    private BitmapFont font;
    private long temp;


    int larg, alt, mortos = 0;
    int posXs[] = new int[10];
    int posYs[] = new int[10];
    double tempo = 10;
    float scale = 2; 
    int pont = 0;
    double cooldown = 0;

    public MainGameScreen2(TwinBeeJogo game) {
        this.game = game;
        batch = new SpriteBatch();
        background = new Texture("background.png");
        bk = new Background(background);
        nave = new Texture("nave.png");
        tiro1 = new Texture("tiro22.png");
        alien1 = new Texture("alien1.png");
        alien2 = new Texture("alien2.png");
        alien3 = new Texture("alien3.png");
        tiro = new Projeteis(tiro1, batch, scale);
        player = new Player(nave, tiro1, tiro);
        alien = new Alien(alien1, alien2, alien3);
        for (int i = 0; i < 10; i++) {
            aliens[i] = new Alien(alien1, alien2, alien3);
        }
        font = new BitmapFont();
        temp = TimeUtils.nanoTime()/1000000000;
        //a
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        bk.draw(batch);
        bk.run();
        font.draw(batch, "Pontuação: " + pont, 30, 550);
        font.draw(batch, "Vida: " + player.vida, 30, 520);
        font.draw(batch, "Tempo: " + (TimeUtils.nanoTime() / 1000000000 - temp), 700, 550);
        tiro.draw(batch);
        alien.draw(batch);
        player.draw(batch);

        // olha posições iguais
        for (int i = 0; i < 10; i++) {
            if (TimeUtils.nanoTime() / 1000000000 > cooldown + 2) {
                if (player.posicaoIgual(boss.getPosX(), boss.getPosY()) == true){
                    cooldown = TimeUtils.nanoTime() / 1000000000;
                }
            }

        }
        alien.posicaoIgual(tiro.getPosX(), tiro.getPosY());

        // ve se player morreu
        if (player.isMorto() == true) {
            game.setScreen(new CreditosScreen(game));
        }
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