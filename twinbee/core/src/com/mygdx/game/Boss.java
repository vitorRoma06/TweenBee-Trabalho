package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class Boss extends Movel {
    private TwinBeeJogo game;
    private Movel tiro;

    private int velX;
    private int velY;
    private int fase;
    private float cooldown;

    public Boss(TwinBeeJogo game, Texture sprite, Movel tiro) {
        this.game = game;
        this.sprite = sprite;
        this.tiro = tiro;
        this.morto = false;
        vida = 30;
        velX = 5;
        velY = 5;
        fase = 0;
        posX = 400;
        posY = 400;
        alt = 200;
        larg = 200;
        ((Projeteis) tiro).setAlien();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(sprite, posX, posY,  larg, alt);
        switch (fase) {
            case 0:
                if (posX + 200 > 799 || posX < 1) {
                    velX = velX * -1;
                }
                posX = posX + velX;
                if (posY + 170 > 599 || posY < 0) {
                    velY = velY * -1;
                }
                posY = posY + velY;
                break;

            case 1:
                if (posX < 1 || posX + 200 > 799) {
                    velX = velX * -1;
                }
                posX = posX + velX;
                if (posY + 170 > 599 || posY < 350) {
                    velY = velY * -1;
                }
                posY = posY + velY;
                if (tiro.isMorto() == true && TimeUtils.nanoTime() / 1000000000> cooldown + 0.5) {
                        tiro.setMorto(false);
                        ((Projeteis) tiro).setPos(posX, posY, sprite.getHeight(), sprite.getWidth());
                        cooldown = (TimeUtils.nanoTime() / 1000000000);
                    }
                    if(tiro.posY < 0){
                        tiro.setMorto(true);
                    }
                break;

            case 2:
                break;
        }

    }

    public void setFase(int fase) {
        switch(fase) {
            case 0:
                break;
            case 1:
                velX = 7;
                velY = -1;
                break;
            case 2:

                break;
        }
    }


}
