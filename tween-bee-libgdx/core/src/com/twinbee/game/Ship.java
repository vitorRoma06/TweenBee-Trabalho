package com.twinbee.game;

import com.badlogic.gdx.graphics.Texture;

public class Ship extends Actor {
    Ship(float x, float y, Texture texture, TwinBeeGame game) {
        super(x, y, texture, game);
    }

    @Override
    void execute() {
        // implement any ship-specific logic here
    }
}
