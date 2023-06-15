package com.twinbee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class Ship extends Actor {
    Ship(float x, float y, Texture texture, TwinBeeGame game) {
        super(x, y, texture, game);
    }

    @Override
    void execute() {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            sprite.translateX(-12);
        }
                else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                    sprite.translateX(12);
                }
                    else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                        sprite.translateY(-12);
                    }   
                        else if (Gdx.input.isKeyPressed(Input.Keys.UP)){
                        sprite.translateY(12);
                    }   
            
        }
    }

