package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Random;
import com.badlogic.gdx.utils.TimeUtils;

public class MainGameScreen2 extends ApplicationAdapter implements Screen  {
    final TwinBeeJogo game;

    private Texture background;
    private Texture nave;
    private Texture tiro1;
    private Texture alien1;
    private Texture alien2;
    private Texture alien3;
    private Texture boss1;
    private Texture tiroAlien;
    private Texture tiroBoss;

    private Sound somTiro;
    private Sound morteNaveSound;

    public SpriteBatch batch;

    private Background bk;

    private Movel player;
    private Projeteis tiro;
    private Alien aliens[] = new Alien[10];
    private Projeteis tiros[] = new Projeteis[10];
    private Movel boss;
    private Movel bossTiro;

    Random rand = new Random();
    private long temp;

    int larg, alt, mortos = 0;
    int posXs[] = new int[10];
    int posYs[] = new int[10];
    double tempo = 10;
    float scale = 2;
    int pont = 0;
    double cooldown = 0;
    double bossCooldown = 0;

    public MainGameScreen2(TwinBeeJogo game) {
        this.game = game;
        batch = new SpriteBatch();

        somTiro = Gdx.audio.newSound(Gdx.files.internal("sounds/tiro.mp3"));
        morteNaveSound = Gdx.audio.newSound(Gdx.files.internal("sounds/morte-nave.mp3"));

        background = new Texture("background.png");
        nave = new Texture("nave.png");
        tiro1 = new Texture("tiro22.png");
        alien1 = new Texture("alien1.png");
        alien2 = new Texture("alien2.png");
        alien3 = new Texture("alien3.png");
        boss1 = new Texture("boss.png");
        tiroAlien = new Texture("tiroAlien_1.png");
        tiroBoss = new Texture("tiroBoss.png");

        bk = new Background(background);
        tiro = new Projeteis(tiro1, batch, scale, somTiro);
        bossTiro = new Projeteis(tiroBoss, batch, scale*4, somTiro);
        player = new Player(nave, tiro);
        boss = new Boss(game, boss1, bossTiro);
        for (int i = 0; i < 10; i++) {
            tiros[i] = new Projeteis(tiroAlien, batch, scale, somTiro);
            aliens[i] = new Alien(alien1, alien2, alien3, morteNaveSound, tiros[i]);
        }
        temp = TimeUtils.nanoTime() / 1000000000;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        bk.draw(batch);
        bk.run();
        game.font.draw(batch, "Pontuação: " + pont, 30, 550);
        game.font.draw(batch, "Vida: " + player.vida, 30, 520);
        game.font.draw(batch, "Tempo: " + (TimeUtils.nanoTime() / 1000000000 - temp), 700, 550);
        tiro.draw(batch);
        bossTiro.draw(batch);
        boss.draw(batch);
        player.draw(batch);

        // olha posições iguais
        if (TimeUtils.nanoTime() / 1000000000 > cooldown + 2) {
            if (player.posicaoIgual(boss.getPosX(), boss.getPosY(), 100, 100) == true) {
                cooldown = TimeUtils.nanoTime() / 1000000000;
            }
        }

        if (TimeUtils.nanoTime() / 1000000000 > bossCooldown + 0.5) {
            if (boss.posicaoIgual(tiro.getPosX(), tiro.getPosY(), 50, 50) == true) {
                bossCooldown = TimeUtils.nanoTime() / 1000000000;
                tiro.setMorto(true);
            }
        }

        //boss
        if(boss.vida == 40){
            ((Boss) boss).setFase(1);
        }
        if(boss.vida == 20){
            ((Boss) boss).setFase(2);
        }


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
