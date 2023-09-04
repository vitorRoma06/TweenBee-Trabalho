package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class Player extends Movel {

    private int spriteNum;
    private int spriteNum2;
    private int num;

    private Texture nave1;
    private Texture nave2;
    private Texture nave3;
    private Texture nave4;

    public Player(Texture sprite, Texture nave1, Texture nave2, Texture nave3, Texture nave4, int num, int PosX,
            int PosY) {
        super(PosX, PosY, sprite.getWidth(), sprite.getHeight());
        posX = 400;
        posY = 100;
        morto = false;
        this.sprite = sprite;
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

}
