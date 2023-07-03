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
    }

    @Override
    public void draw(SpriteBatch batch) {
        switch (fase) {
            case 0:
            if(posX +sprite.getWidth() > 799 || posX < 1){
                velX = velX*-1;
            }
            posX = posX + velX;
            if(posY +sprite.getHeight() > 599 || posY < 300){
                velY = velY*-1;
            }
            posY = posY + velY;
            break;
            case 1:
            break;
            case 2:
            break;
        }
        batch.draw(sprite, posX, posY);
    }
}
