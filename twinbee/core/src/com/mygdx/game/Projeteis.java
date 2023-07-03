package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Projeteis extends Movel {
    private float scale;
    private int velY;

    public Projeteis(Texture sprite, SpriteBatch batch, float scale) {
        posY = 599;
        vida = 1;
        morto = true;
        velY = 20;
        this.sprite = sprite;
        this.scale = scale;
    }

    public void setPos(int posOriX, int posOriY, int alt, int larg) {
        posX = posOriX + larg/2 - 20; // Ajusta a posição horizontal do projétil
        posY = posOriY + alt - 30;
    }

    public void setAlien(){
        velY = velY*-1;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (morto == false) {
            posY = posY + velY;
            batch.draw(sprite, posX, posY, 20 * scale, 20 * scale);
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
