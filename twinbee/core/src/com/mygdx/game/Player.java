package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Player extends Movel {

    private Texture tiro1Sprite;

    Projeteis tiro;

    private int powerup;

    public Player(Texture sprite, Texture tiro1Sprite, Projeteis tiro) {
        vida = 3;
        posX = 400;
        posY = 100;
        morto = false;
        this.sprite = sprite;
        powerup = 1;
        this.tiro1Sprite = tiro1Sprite;
        this.tiro = tiro;
        
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (Gdx.input.isKeyPressed(Input.Keys.W) && posY < (600 - sprite.getHeight())) {
            posY = posY + 5;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A) && posX > 0) {
            posX = posX - 5;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S) && posY > 0) {
            posY = posY - 5;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D) && posX < (800 - sprite.getWidth())) {
            posX = posX + 5;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if(tiro.isMorto() == true){
                tiro.setMorto(false);
                tiro.setPos(posX, posY, sprite.getHeight(), sprite.getWidth()); 
            }
            
        }
        batch.draw(sprite, posX, posY);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

}