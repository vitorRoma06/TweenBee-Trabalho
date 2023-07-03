package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class Alien extends Movel {
    private int vida;
    private Texture sprite1;
    private Texture sprite2;

    private int tipo;
    private int velX;
    private int velY;
    private float cooldown;

    Projeteis tiro;
    Random rand = new Random();

    private Sound morteNaveSound;

    public Alien(Texture sprite, Texture sprite2, Texture sprite1, Sound morteNaveSound, Projeteis tiro) {

        this.sprite = sprite;
        this.sprite2 = sprite2;
        this.sprite1 = sprite1;
        this.tiro = tiro;
        morto = true;
        vida = 1;
        posX = 0;
        posY = 0;
        velX = 4;
        velY = -4;
        this.morteNaveSound = morteNaveSound;

        tiro.setAlien();
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (morto == false) {
            switch (tipo) {
                case 0:
                    if ((posX + 50) > 799 || posX < 0) {
                        velX = velX * (-1);
                    }
                    posX = posX + velX;
                    if ((posY + 50) > 599 || posY < 400) {
                        velY = velY * (-1);
                    }
                    posY = posY + velY;
                    if (tiro.isMorto() == true && TimeUtils.nanoTime() / 1000000000> cooldown + 3 && rand.nextInt(80) == 0 ) {
                        tiro.setMorto(false);
                        tiro.setPos(posX, posY, sprite.getHeight(), sprite.getWidth());
                        cooldown = (TimeUtils.nanoTime() / 1000000000);
                    }
                    if(tiro.posY < 0){
                        tiro.setMorto(true);
                    }
                    batch.draw(sprite, posX, posY, 50, 50);
                    break;
                case 1:
                    if ((posX + 50) > 800 || posX < 0) {
                        velX = velX * (-1);
                    }
                    posX = posX + velX;
                    if ((posY + 50) > 599 || posY < 1) {
                        velY = velY * (-1);
                    }
                    posY = posY + velY;
                    batch.draw(sprite1, posX, posY, 50, 50);
                    break;
                case 2:
                    if ((posX + 50) > 800 || posX < 0) {
                        velX = velX * (-1);
                    }
                    posX = posX + velX;
                    if ((posY + 50) > 599 || posY < 1) {
                        velY = velY * (-1);
                    }
                    posY = posY + velY;
                    batch.draw(sprite2, posX, posY, 50, 50);
                    break;
                default:
                    batch.draw(sprite, posX, posY, 50, 50);
                    break;
            }
        }

    }

    @Override
    public boolean posicaoIgual(int posX1, int posY1, int height, int width) {
        for (int i = 0; i < 35; i++) {
            for (int j = 0; j < 35; j++) {
                if ((posX + j == posX1) && (posY1 == posY + i)) {
                    dano();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void dano() {
        vida--;
        if (vida < 1) {
            morto = true;
            posX = 2000;
            posY = 2000;
            morteNaveSound.play();
        }
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
        if (tipo == 1) {
            velX = velX * -1;
        }
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getTipo() {
        return tipo;
    }

    @Override
    public boolean isMorto() {
        return morto;
    }

    public void setMorto(boolean morto) {
        this.morto = morto;
    }
}
