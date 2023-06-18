package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    Texture texture;
    int ay, by;

    Background(Texture texture){
        this.texture = texture;
        ay=0;
        by = texture.getHeight();
    }

    void run(){
        ay -= 2;
        by -= 2;
        //se a textura ficar fora da tela, ela será desenhada para cima
        if (ay <= -texture.getHeight()){
            ay = by + texture.getHeight();
        }
        if (by <= -texture.getHeight()){
            by = ay + texture.getHeight();
        }
    }

    void draw(SpriteBatch batch){
        batch.draw(texture, 0, ay);
        batch.draw(texture, 0, by);
    }
}
