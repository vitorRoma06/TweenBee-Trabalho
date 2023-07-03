package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Random;

public class MainGameScreen extends ApplicationAdapter implements Screen {

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

    Random rand = new Random();
    private BitmapFont font;

    int larg, alt, mortos = 0;
    int posXs[] = new int[10];
    int posYs[] = new int[10];
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
        alien2 = new Texture("alien2.png");
        alien3 = new Texture("alien3.png");
        tiro = new Projeteis(tiro1, batch, scale);
        player = new Player(nave, tiro1, tiro);
        alien = new Alien(alien1, alien2, alien3);
        for (int i = 0; i < 10; i++) {
            aliens[i] = new Alien(alien1, alien2, alien3);
        }
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.draw(batch, "Pontuação",30,550);
        bk.draw(batch);
        bk.run();
        tiro.draw(batch);
        mortos=0;
        for (int i = 0; i < 10; i++) {
            if (aliens[i].isMorto() == true) {
                mortos = mortos + 1;
            }
        }
        ;
        if (mortos == 10) {
            alienSpawn(5,0);
            alienSpawn(10,5);
            mortos = 0;
        }
        for(int i = 0; i < 10; i++){
            if(i < 5){
                aliens[i].draw(batch);
            }else {
                aliens[i].draw(batch);
            }
            aliens[i].posicaoIgual(tiro.getPosX(), tiro.getPosY());
        }

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
                            }else{

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
                            }else{

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
                            }else{

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
