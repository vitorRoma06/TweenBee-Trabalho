package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Movel {
    protected int posX;
    protected int posY;
    protected int vida;
    protected int larg;
    protected int alt;
    public boolean morto;
    protected Texture sprite;
    TwinBeeJogo game;

    public boolean posicaoIgual(int posX1, int posY1, int height, int width) {
        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < larg; j++) {
                for (int m = 0; m < width; m++) {
                    for (int n = 0; n < height; n++) {
                        if ((posX + i == posX1 + m) && (posY + j == posY1 + n)) {
                            dano();
                            return true;
                        }
                    }

                }

            }
        }

        return false;
    }

    public void dano() {
        vida--;
        if (vida < 1) {
            morto = true;
            posX = 2000;
            posY = 2000;
        }
    }

    public void draw(SpriteBatch batch) {
        if (morto = false) {
            batch.draw(sprite, posX, posY);
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

    public boolean isMorto() {
        return morto;
    }

    public void setMorto(boolean morto) {
        this.morto = morto;
    }

    public boolean colisaoAlien(int posX2, int posY2) {
        return false;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

}