package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class Player extends Movel {

    float cooldown;
    Projeteis tiro;

    private Texture nave1;
    private Texture nave2;
    private Texture nave3;
    private Texture nave4;

    private int spriteNum;
    private int spriteNum2;

    // server
    int num;
    String message;
    String message2 ="a";

    public Player(Texture sprite, Projeteis tiro, Texture nave1, Texture nave2, Texture nave3, Texture nave4, int num) {
        vida = 10;
        posX = 400;
        posY = 100;
        morto = false;
        this.sprite = sprite;
        this.tiro = tiro;
        alt = sprite.getHeight();
        larg = sprite.getWidth();
        this.nave1 = nave1;
        this.nave2 = nave2;
        this.nave3 = nave3;
        this.nave4 = nave4;
        this.num = num;

    }

    @Override
    public void draw(SpriteBatch batch) {
        if(num == 0){
            if (Gdx.input.isKeyPressed(Input.Keys.W) && posY < (600 - sprite.getHeight())) {
            posY = posY + 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && posX > 0) {
            posX = posX - 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) && posY > 0) {
            posY = posY - 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && posX < (800 - sprite.getWidth())) {
            posX = posX + 5;
        }
        }
        

        //player online
        if (num == 1) {
            switch (message) {
                case "up":
                    if (posY < (600 - sprite.getHeight())) {
                        posY = posY + 5;
                    }
                break;
                case "down":
                    if(posY > 0){
                        posY = posY - 5;
                    }
                break;
                case "left":
                    if(posX > 0){
                       posX = posX - 5; 
                    }
                break;
                case "right":
                    if(posX < (800 - sprite.getWidth())){
                        posX = posX + 5;
                    }
                break;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && num == 0) {
            if (tiro.isMorto() == true && TimeUtils.nanoTime() / 1000000 > (cooldown) + 500) {
                tiro.setMorto(false);
                tiro.setPos(posX, posY, sprite.getHeight(), sprite.getWidth());
                cooldown = TimeUtils.nanoTime() / 1000000;
            }
        }

        //player2
        if(message2.equals("space") && num == 1){
            if (tiro.isMorto() == true && TimeUtils.nanoTime() / 1000000 > (cooldown) + 500) {
                tiro.setMorto(false);
                tiro.setPos(posX, posY, sprite.getHeight(), sprite.getWidth());
                cooldown = TimeUtils.nanoTime() / 1000000;
            }
        }

        if (spriteNum % 60 == 0) {
            spriteNum2++;
        }
        if (spriteNum2 % 5 == 0) {
            batch.draw(sprite, posX, posY, larg, alt);
        } else if (spriteNum2 % 5 == 1) {
            batch.draw(nave1, posX, posY, larg, alt);
        } else if (spriteNum2 % 5 == 2) {
            batch.draw(nave2, posX, posY, larg, alt);
        } else if (spriteNum2 % 5 == 3) {
            batch.draw(nave3, posX, posY, larg, alt);
        } else if (spriteNum2 % 5 == 4) {
            batch.draw(nave4, posX, posY, larg, alt);
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setMessage(String message, String message2) {
        this.message = message;
        this.message2 = message2;
    }

}