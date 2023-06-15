package com.twinbee.game;

import com.badlogic.gdx.graphics.Texture;

public class Enemy extends Actor{
    static final int RIGHT = 0;
    static final int LEFT = 1;
    int direcao = RIGHT;

    Enemy(float x, float y, Texture texture, TwinBeeGame game) {
        super(x, y, texture, game);
    }

    @Override
    void execute(){
        sprite.translateY(-5);
        if (direcao == RIGHT){
            sprite.translateX(5);
            if(sprite.getX() + sprite.getWidth() > game.w){
                direcao = LEFT;
            }
        } else {
            sprite.translateX(5);
            if(sprite.getX() < 0){
                direcao = RIGHT;
            }
        }
    }
    
}
