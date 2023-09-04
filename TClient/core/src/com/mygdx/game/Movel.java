package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Movel {
    protected int posX;
    protected int posY;
    protected int larg;
    protected int alt;
    public boolean morto;
    private Sound morte;
    private int tipo=10;
    private Texture sprite;
    
    public Movel(int posX, int posY, int larg, int alt, Sound morte) {
        this.posX = posX;
        this.posY = posY;
        this.larg = larg;
        this.alt = alt;
        this.morte = morte;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setSprite(Texture sprite) {
        this.sprite = sprite;
    }

    public int getTipo() {
        return tipo;
    }

    public void draw(SpriteBatch batch) {
        if(posX < 1500){
            if(tipo == 30){
                batch.draw(sprite, posX, posY,20*2,20*2);
            }else{
                batch.draw(sprite, posX, posY,50,50);
            }
        }
        
    }
   
}
