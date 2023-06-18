package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;    
import java.awt.event.*;    

public class Movel{
    private int posX;
    private int posY;
    private int vida;
    private boolean morto;
    private Texture sprite;
    TwinBeeJogo game;


    public void posicaoIgual(int posX1, int posY1){
        if(posX == posX1 && posY1 == posY){
            dano();
        }
    }

    public void dano(){
        vida--;
        if(vida == 0){
            morto = true;
            posX = 2000;
            posY = 2000;
        }
    }

    public void draw(SpriteBatch batch){
        if(morto = false){
            batch.draw(sprite, posX, posY);
        }
        
    }
    
}