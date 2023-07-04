package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Random;
import com.badlogic.gdx.utils.TimeUtils;

public class MainGameScreen extends ApplicationAdapter implements Screen {

    final TwinBeeJogo game;

    private Texture background;
    private Texture nave;
    private Texture tiro1;
    private Texture alien1;
    private Texture alien2;
    private Texture alien3;
    private Texture tiroAlien;

    private Music backgroundMusic;
    
    private Sound morteNaveSound;
    private Sound somTiro;
    private Sound hitSound;

    private Movel player;
    private Projeteis tiro;
    public SpriteBatch batch;
    private Background bk;

    private Alien aliens[] = new Alien[10];
    private Projeteis tiros[] = new Projeteis[10];

    Random rand = new Random();
    private long temp;

    boolean isGameOver = false;

    int larg, alt, mortos = 0;
    int posXs[] = new int[10];
    int posYs[] = new int[10];
    double tempo = 10;
    double cooldown = 0;
    float scale = 2;
    int pont = 0;

    public MainGameScreen(TwinBeeJogo game) {
        this.game = game;
        batch = new SpriteBatch();

        morteNaveSound = Gdx.audio.newSound(Gdx.files.internal("sounds/morte-nave.mp3"));
        somTiro = Gdx.audio.newSound(Gdx.files.internal("sounds/tiro.mp3"));
        hitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hitsound.mp3"));
        
        background = new Texture("background.png");
        nave = new Texture("nave.png");
        tiro1 = new Texture("tiro22.png");
        alien1 = new Texture("alien1.png");
        alien2 = new Texture("alien2.png");
        alien3 = new Texture("alien3.png");
        tiroAlien = new Texture("tiroAlien_1.png");

        bk = new Background(background);
        tiro = new Projeteis(tiro1, batch, scale, somTiro);
        player = new Player(nave, tiro);
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
        game.font.draw(batch, "Pontuacao: " + pont, 30, 550);
        game.font.draw(batch, "Vida: " + player.vida, 30, 520);
        game.font.draw(batch, "Tempo: " + (TimeUtils.nanoTime() / 1000000000 - temp), 700, 550);
        tiro.draw(batch);
        player.draw(batch);
        mortos = 0;

        // ve se aliens estão mortos
        for (int i = 0; i < 10; i++) {
            if (aliens[i].isMorto() == true) {
                mortos = mortos + 1;
            }
        }
        // tempo desde que os aliens morreram
        if (mortos == 10 && tempo == 0) {
            tempo = TimeUtils.nanoTime() / 1000000000;
        }
        // spawna os aliens
        if (mortos == 10 && TimeUtils.nanoTime() / 1000000000 > tempo + 7) {
            alienSpawn(5, 0);
            alienSpawn(10, 5);
            mortos = 0;
            tempo = 0;
        }

        // olha posições iguais e draw alien e tiros
        for (int i = 0; i < 10; i++) {
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

        // ve se player morreu
        if (!isGameOver) {
            if (player.isMorto()) {
                backgroundMusic.stop();
                isGameOver = true;
                game.setScreen(new GameOverScreen(game));
            }
        } else {
            if (Gdx.input.justTouched()) {
                isGameOver = false;
                backgroundMusic.play();
            }
        }

        if(pont >= 1){
            game.setScreen(new BossScreen(game));
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
        backgroundMusic.dispose();
    }

    @Override
    public void show() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/music-start.mp3"));
        backgroundMusic.setLooping(true); // Define a música para repetir
        backgroundMusic.setVolume(0.2f);
        backgroundMusic.play(); // Inicia a reprodução da música
    }

    public void alienSpawn(int limite, int comeco) {
        int tipos = rand.nextInt(3);
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
