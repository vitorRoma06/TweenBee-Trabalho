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
        
    }

    void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
