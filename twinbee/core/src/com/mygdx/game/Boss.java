package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Boss extends Movel{
    private TwinBeeJogo game;

    private int velX;
    private int velY;
    private int fase;
    
    public Boss(TwinBeeJogo game, Texture sprite) {
        this.game = game;
        this.sprite = sprite;
        this.morto = false;
        vida = 60;
        velX = 5;
        velY = 5;
        fase = 0;
        posX = 400;
        posY = 300;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(sprite, posX, posY, 200, 200);
        switch (fase) {
            case 0:
            if(posX + 200 > 799 || posX < 1){
                velX = velX*-1;
            }
            posX = posX + velX;
            if(posY + 170 > 599 || posY < 0){
                velY = velY*-1;
            }
            posY = posY + velY;
            break;
            case 1:
            
            break;
            case 2:
            break;
        }
        
    }
}
