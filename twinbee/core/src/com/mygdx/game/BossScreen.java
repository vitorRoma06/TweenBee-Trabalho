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

public class BossScreen extends ApplicationAdapter implements Screen  {
    final TwinBeeJogo game;

    private Texture background;
    private Texture nave;
    private Texture tiro1;
    private Texture alien1;
    private Texture alien2;
    private Texture alien3;
    private Texture boss1;
    private Texture boss2;
    private Texture tiroAlien;
    private Texture tiroBoss;

    private Texture red;
    private Texture green;

    private Sound somTiro;
    private Sound morteNaveSound;
    private Sound hitSound;

    public SpriteBatch batch;

    private Background bk;

    private Movel player;
    private Projeteis tiro;
    private Alien aliens[] = new Alien[5];
    private Projeteis tiros[] = new Projeteis[5];
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

    public BossScreen(TwinBeeJogo game) {
        this.game = game;
        batch = new SpriteBatch();

        somTiro = Gdx.audio.newSound(Gdx.files.internal("sounds/tiro.mp3"));
        morteNaveSound = Gdx.audio.newSound(Gdx.files.internal("sounds/morte-nave.mp3"));
        hitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hitsound.mp3"));

        background = new Texture("background.png");
        nave = new Texture("nave.png");
        tiro1 = new Texture("tiro22.png");
        alien1 = new Texture("alien1.png");
        alien2 = new Texture("alien2.png");
        alien3 = new Texture("alien3.png");
        boss1 = new Texture("boss.png");
        boss2 = new Texture("boss2.png");
        tiroAlien = new Texture("tiroAlien_1.png");
        tiroBoss = new Texture("tiroBoss.png");
        red = new Texture("red.png");
        green = new Texture("green.png");

        bk = new Background(background);
        tiro = new Projeteis(tiro1, batch, scale, somTiro);
        bossTiro = new Projeteis(tiroBoss, batch, scale*4, somTiro);
        player = new Player(nave, tiro);
        boss = new Boss(game, boss1, bossTiro, boss2);
        for (int i = 0; i < 5; i++) {
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
        for(int i = 0; i < 30; i++){
            batch.draw(green, 0+(i*5), 595,20,20);
        }
        for(int i = 0; i < boss.vida; i++){
            batch.draw(red, 0+(i*5), 595,20,20);
         }
        tiro.draw(batch);
        bossTiro.draw(batch);
        boss.draw(batch);
        player.draw(batch);

        // olha posições iguais
        if (TimeUtils.nanoTime() / 1000000000 > cooldown + 2) {
            if (player.posicaoIgual(boss.getPosX(), boss.getPosY(), boss.larg, boss.alt) == true) {
                hitSound.play();
                cooldown = TimeUtils.nanoTime() / 1000000000;
            }
            if (player.posicaoIgual(bossTiro.getPosX(), bossTiro.getPosY(), bossTiro.larg, bossTiro.alt) == true) {
                hitSound.play();
                cooldown = TimeUtils.nanoTime() / 1000000000;
            }
        }

        if (TimeUtils.nanoTime() / 1000000 > bossCooldown + 500) {
            if (boss.posicaoIgual(tiro.getPosX(), tiro.getPosY(), 120, 120) == true) {
                bossCooldown = TimeUtils.nanoTime() / 1000000;
                morteNaveSound.play();
                tiro.setMorto(true);
            }
        }

        //ve se alien morto
        for (int i = 0; i < 5; i++) {
            if (aliens[i].isMorto() == true) {
                mortos = mortos + 1;
            }
        }

         // tempo desde que os aliens morreram
        if (mortos == 5 && tempo == 0) {
            tempo = TimeUtils.nanoTime() / 1000000000;
        }

        // spawna os aliens
        if (mortos == 5 && TimeUtils.nanoTime() / 1000000000 > tempo + 5 && ((Boss) boss).getFase() == 2) {
            alienSpawn(5, 0);
            mortos = 0;
            tempo = 0;
        }
        for(int i = 0; i < 5; i++){
            aliens[i].draw(batch);
            if(aliens[i].getTipo() == 0) {
                tiros[i].draw(batch);
            }

            if (aliens[i].posicaoIgual(tiro.getPosX(), tiro.getPosY(), 35, 35) == true) {
                pont = pont + 100;
                tiro.setMorto(true);
            }
        
            if (TimeUtils.nanoTime() / 1000000000 > cooldown + 2) {
                if (player.posicaoIgual(aliens[i].getPosX(), aliens[i].getPosY(), 40, 35) == true) {
                    cooldown = TimeUtils.nanoTime() / 1000000000;
                    hitSound.play();
                }else if (player.posicaoIgual(tiros[i].getPosX(), tiros[i].getPosY(), 35, 35) == true) {
                    cooldown = TimeUtils.nanoTime() / 1000000000;
                    hitSound.play();
                    tiros[i].setMorto(true);
                }
            }
        }

        //boss
        if(boss.vida == 20){
            ((Boss) boss).setFase(1);
        }
        if(boss.vida == 10){
            ((Boss) boss).setFase(2);
        }


        // ve se player morreu
        if (player.isMorto() == true) {
            game.setScreen(new GameOverScreen(game));
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

    public void alienSpawn(int limite, int comeco) {
        int tipos = 0;
        int soma = 0;
        for (int i = comeco; i < limite; i++) {
            aliens[i].setTipo(tipos);
            int j = comeco;
            aliens[i].setMorto(false);
            switch ((aliens[i].getTipo())) {
                case 0:
                    // pegando posX
                    do {
                        posXs[i] = rand.nextInt(0, 750);
                        if (posXs[i] == posXs[j]) {
                            soma++;
                            j++;
                        } else {

                        }
                    } while (soma == 5);
                    aliens[i].setPosX(posXs[i]);
                    j = 0;
                    soma = 0;
                    // pegando posY
                    do {
                        posYs[i] = rand.nextInt(450, 550);
                        if (posYs[i] != posYs[j]) {
                            soma++;
                            j++;
                        }
                    } while (soma == 10);
                    aliens[i].setPosY(posYs[i]);
                    break;
                case 1:
                    // pegando posX
                    do {
                        posXs[i] = rand.nextInt(500, 750);
                        if (posXs[i] == posXs[j]) {
                            soma++;
                            j++;
                        } else {

                        }
                    } while (soma == 10);
                    aliens[i].setPosX(posXs[i]);
                    j = 0;
                    soma = 0;
                    // pegando posY
                    do {
                        posYs[i] = rand.nextInt(450, 550);
                        if (posYs[i] != posYs[j]) {
                            soma++;
                            j++;
                        }
                    } while (soma == 10);
                    aliens[i].setPosY(posYs[i]);
                    break;
                case 2:
                    // pegando posX
                    do {
                        posXs[i] = rand.nextInt(0, 300);
                        if (posXs[i] == posXs[j]) {
                            soma++;
                            j++;
                        } else {

                        }
                    } while (soma == 10);
                    aliens[i].setPosX(posXs[i]);
                    j = 0;
                    soma = 0;
                    // pegando posY
                    do {
                        posYs[i] = rand.nextInt(450, 550);
                        if (posYs[i] != posYs[j]) {
                            soma++;
                            j++;
                        }
                    } while (soma == 10);
                    aliens[i].setPosY(posYs[i]);
                    break;
                default:
                    aliens[i].setPosX(600);
                    aliens[i].setPosY(600);

                    break;

            }
        }
    }
}
