package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Alien extends Movel {

    private int tipo;
    private int velX;
    private int velY;

    public Alien(Texture sprite, int tipo, int screenWidth) {
        this.sprite = sprite;
        if (tipo == 0) {
            velX = 3;
            velY = 3;
        }
        vida = 1;
        Random random = new Random();
        posX = random.nextInt(screenWidth - sprite.getWidth()); // Posição aleatória dentro da largura da tela
        posY = 500; // Posicionado no topo da tela
        morto = false;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (morto == false) {
            switch (tipo) {
                case 0:
                    if ((posX + 50) > 799 || posX < 0) {
                        velX = velX * (-1);
                    }
                    posX = posX + velX;
                    if ((posY + 50) > 599 || posY < 400) {
                        velY = velY * (-1);
                    }
                    posY = posY + velY;
                    break;
                case 1:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        }
        batch.draw(sprite, posX, posY, 50, 50);
    }

    @Override
    public void posicaoIgual(int posX1, int posY1) {
        for (int i = 0; i < sprite.getWidth()/2; i++) {
            for (int j = 0; j < sprite.getHeight()/2; j++) {
                if ((posX + j == posX1) && (posY1 == posY + i)) {
                    dano();
                    return; // Sai do método se o projétil atingir o alien
                }
            }
            if ((posX == posX1 + i) && (posY1 == posY)) {
                dano();
            }
        }
    }

    @Override
    public void dano() {
        vida--;
        if (vida < 1) {
            morto = true;
            posX = 2000;
            posY = 2000;
        }
    }

}
