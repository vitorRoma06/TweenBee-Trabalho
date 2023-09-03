package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class Boss extends Movel {
    private TwinBeeJogo game;
    private Movel tiro;
    private Texture sprite2;
    private Texture sprite3;
    private Texture sprite2_1;
    private Texture sprite2_2;
    private Texture sprite3_1;

    private int velX;
    private int velY;
    private int fase;
    private float cooldown;
    int spriteNum = 0;
    int spriteNum2 = 0;

    public Boss(TwinBeeJogo game, Texture sprite, Movel tiro, Texture sprite2, Texture sprite2_1, Texture sprite2_2, Texture sprite3, Texture sprite3_1) {
        this.game = game;
        this.sprite = sprite;
        this.sprite2 = sprite2;
        this.sprite3 = sprite3;
        this.sprite2_1 = sprite2_1;
        this.sprite2_2 = sprite2_2;
        this.sprite3_1 = sprite3_1;
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
        spriteNum++;
        switch (fase) {
            case 0:
                if (posX + 200 > 799 || posX < 1) {
                    velX = velX * -1;
                }
                posX = posX + velX;
                if (posY + 200 > 599 || posY < 0) {
                    velY = velY * -1;
                }
                posY = posY + velY;
                batch.draw(sprite, posX, posY,  larg, alt);
                break;
            case 1:
                if (posX < 1 || posX + 200 > 799) {
                    velX = velX * -1;
                }
                posX = posX + velX;
                if (posY + 200 > 599 || posY < 350) {
                    velY = velY * -1;
                }
                posY = posY + velY;
                if (tiro.isMorto() == true && TimeUtils.nanoTime() / 1000000000 > cooldown + 0.5) {
                        tiro.setMorto(false);
                        ((Projeteis) tiro).setPos(posX, posY, sprite.getHeight(), sprite.getWidth());
                        cooldown = (TimeUtils.nanoTime() / 1000000000);
                    }
                    if(tiro.posY < 0){
                        tiro.setMorto(true);
                    }
                if(spriteNum % 60 == 0){
                    spriteNum2++;
                }
                if(spriteNum2 % 3 == 0){
                    batch.draw(sprite2, posX, posY,  larg, alt);
                }else if(spriteNum2 % 3 == 1){
                    batch.draw(sprite2_1, posX, posY,  larg, alt);
                } else{
                    batch.draw(sprite2_2, posX, posY,  larg, alt);
                }
                break;

            case 2:
            if (posX < 1 || posX + 200 > 799) { 
                    velX = velX * -1;
                }
                posX = posX + velX;
                if (posY + 200 > 599 || posY < 0) {
                    velY = velY * -1;
                }
                posY = posY + velY;
                if (tiro.isMorto() == true && TimeUtils.nanoTime() / 1000000000 > cooldown + 0.5) {
                        tiro.setMorto(false);
                        ((Projeteis) tiro).setPos(posX, posY, sprite.getHeight(), sprite.getWidth());
                        cooldown = (TimeUtils.nanoTime() / 1000000000);
                    }
                    if(tiro.posY < 0){
                        tiro.setMorto(true);
                    }
                
                if(spriteNum % 60 == 0){
                    spriteNum2++;
                }
                if(spriteNum2 % 2 == 0){
                    batch.draw(sprite3, posX, posY,  larg, alt);
                }else{
                    batch.draw(sprite3_1, posX, posY,  larg, alt);
                }
                
                break;
        }

    }

    public void setFase(int fase) {
        switch(fase) {
            case 0:
            this.fase = 0;
                break;
            case 1:
                velX = 7;
                velY = -3;
                posY = 351;
                dano();
                this.fase = 1;
                break;
            case 2:
                velX = 8;
                velY = -4;
                this.fase = 2;
                dano();
                break;
        }
    }

    public int getFase() {
        return fase;
    }


}
