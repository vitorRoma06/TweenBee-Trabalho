package com.twinbee.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Actor {
    TwinBeeGame game;
    Sprite sprite;
    boolean dead = false;
    float gravity = 0.5f;

    Actor(float x, float y, Texture texture, TwinBeeGame game) {
        this.game = game;
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(x, y);
    }

    void execute() {
        
    }
    
    void run() {
        execute();
        //mecanismo para a nave não sair da tela
        sprite.setX(clamp(sprite.getX(), 0, game.w - sprite.getWidth()));
    }

    void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    static float clamp(float value, float min, float max){
        if(value <min) return min;
        if(value > max) return max;
        return value;
    }
}
