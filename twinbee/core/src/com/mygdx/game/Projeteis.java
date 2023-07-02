package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Projeteis extends Movel {
    private int posX;
    private int posY;
    private int vida;
    private boolean morto;
    private Texture sprite;

    public Projeteis(Texture sprite, SpriteBatch batch) {
        posY = 599;
        vida = 1;
        morto = false;
        this.sprite = sprite;
    }

    public void setPos(int posOriX, int posOriY, int alt, int larg) {
        posX = posOriX + larg/2;
        posY = posOriY + alt;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (morto == false) {
            posY = posY + 7;
            batch.draw(sprite, posX, posY, 20, 20);
            if(posY > 600){
                morto = true;
            }
        }

    }

    public boolean isMorto() {
        return morto;
    }

    public void setMorto(boolean morto) {
        this.morto = morto;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    

}
