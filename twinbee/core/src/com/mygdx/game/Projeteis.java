package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;    
import java.awt.event.*; 

public class Projeteis extends Movel{
    private int posX;
    private int posY;
    private int vida;
    private boolean morto;
    private Texture sprite;


    public Projeteis(Texture sprite, SpriteBatch batch) {
        vida = 1;
        morto = false;
        this.sprite = sprite;
    }

    public void setPos(int posOriX, int posOriY, int alt){
        posX = posOriX;
        posY = posOriY + alt;
    }
    
    @Override
    public void draw(SpriteBatch batch){
        if(morto = false){
            posY = posY + 5;
            batch.draw(sprite, posX, posY);
        }
        
    }
    
}
