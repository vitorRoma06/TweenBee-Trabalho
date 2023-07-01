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
    private int veloc;

    public Alien(Texture sprite, int tipo) {
        this.sprite = sprite;
        if(tipo == 0){
            veloc = 1;
        }
        vida = 1;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (morto == false) {
            switch (tipo) {
                case 0:
                if(posX > 799 || posX < 0){
                veloc = veloc * (-1);    
                }posX = posX + veloc;
                    break;
                case 1:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        }
        batch.draw(sprite, posX, posY);
    }

}
