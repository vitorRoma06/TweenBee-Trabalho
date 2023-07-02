package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Movel {
    private int posX;
    private int posY;
    private int vida;
    private boolean morto;
    private Texture sprite;
    TwinBeeJogo game;

    public void posicaoIgual(int posX1, int posY1) {
        for (int i = 0; i < sprite.getWidth(); i++) {
            for (i = 0; i < sprite.getHeight(); i++){
                if ((posX == posX1) && (posY1 == posY+i)) {
                dano();
            }
            }
            if ((posX == posX1+i) && (posY1 == posY)) {
                dano();
            }
        }
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

}