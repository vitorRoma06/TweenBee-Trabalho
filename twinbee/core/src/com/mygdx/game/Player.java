package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;


public class Player extends Movel {

    float cooldown;
    Projeteis tiro;

    private int powerup;

    public Player(Texture sprite,  Projeteis tiro) {
        vida = 3;
        posX = 400;
        posY = 100;
        morto = false;
        this.sprite = sprite;
        powerup = 1;
        this.tiro = tiro;
        alt = sprite.getHeight();
        larg = sprite.getWidth();
        
        
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (Gdx.input.isKeyPressed(Input.Keys.W) && posY < (600 - sprite.getHeight())) {
            posY = posY + 5;
        }if (Gdx.input.isKeyPressed(Input.Keys.A) && posX > 0) {
            posX = posX - 5;
        }if (Gdx.input.isKeyPressed(Input.Keys.S) && posY > 0) {
            posY = posY - 5;
        } if (Gdx.input.isKeyPressed(Input.Keys.D) && posX < (800 - sprite.getWidth())) {
            posX = posX + 5;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if(tiro.isMorto() == true && TimeUtils.nanoTime()/1000000 > (cooldown) + 500){
                tiro.setMorto(false);
                tiro.setPos(posX, posY, sprite.getHeight(), sprite.getWidth());
                cooldown = TimeUtils.nanoTime() / 1000000; 
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