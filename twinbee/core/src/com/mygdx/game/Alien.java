package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Alien extends Movel {
    private int posX;
    private int posY;
    private int vida;
    private boolean morto;
    private Texture sprite;

    private int tipo;
    private int velX;
    private int velY;

    public Alien(Texture sprite) {
        this.sprite = sprite;
        morto = true;
        vida = 1;
        posX = 0;
        posY = 0;
        velX = 3;
        velY = 3;
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
                    break;
                default:
                    break;
            }
        }
        batch.draw(sprite, posX, posY, 50, 50);
    }

    @Override
    public void posicaoIgual(int posX1, int posY1) {
        for (int i = 0; i < 35; i++) {
            for (int j = 0; j < 35; j++) {
                if ((posX + j == posX1) && (posY1 == posY + i)) {
                    dano();
                }
            }
        }
    }

    @Override
    public void dano() {
        vida--;
        if (vida < 1) {
            morto = true;
            posX = 2000;
            posY = 2000;
        }
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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
    public boolean isMorto(){
        return morto;
    }

    public void setMorto(boolean morto) {
        this.morto = morto;
    }

    

    

}
