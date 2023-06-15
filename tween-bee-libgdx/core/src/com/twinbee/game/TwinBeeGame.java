package com.twinbee.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class TwinBeeGame extends ApplicationAdapter {
    public SpriteBatch batch; // O SpriteBatch lida com o desenho de imagens 2D
    Texture background; // Textura para a imagem de fundo
    Background bk; // Objeto Background para gerenciar o plano de fundo em movimento
    Ship ship; // Objeto Ship (nave)
    Texture textureShip; // Textura para a imagem da nave
    int w, h; // Largura e altura da tela do jogo

    @Override
	//É importante lembrar que o create é como se fosse um construtor
    public void create() {
        w = Gdx.graphics.getWidth(); // Obtém a largura da tela
        h = Gdx.graphics.getHeight(); // Obtém a altura da tela
        batch = new SpriteBatch(); // Cria um novo SpriteBatch para desenhar imagens

        background = new Texture("background.png"); // Carrega a imagem de fundo
        textureShip = new Texture("ship.png"); // Carrega a imagem da nave

        bk = new Background(background); // Cria o objeto Background
        ship = new Ship(w / 2f - textureShip.getWidth() / 2f, 10, textureShip, this); // Cria o objeto Ship na metade da tela
    }

    public void execute() {
        bk.run(); // Atualiza o movimento do plano de fundo
        ship.execute(); // Executa a lógica relacionada à nave 
    }

    @Override
    public void render() {
        execute(); // Executa a lógica do jogo

        ScreenUtils.clear(1, 0, 0, 1); // Limpa a tela com a cor vermelha

        batch.begin(); // Inicia o desenho
        bk.draw(batch); // Desenha o plano de fundo
        ship.draw(batch); // Desenha a nave
        batch.end(); // Encerra o desenho

        // O código acima desenha o plano de fundo e a nave na tela
        // A ordem em que os objetos são desenhados determina a camada em que eles aparecem na tela
        // Neste caso, o plano de fundo é desenhado primeiro e, em seguida, a nave, para que a nave apareça acima
		// Caso a ordem seja invertida, o fundo será desenhado em cima da nave prejudicando o funcionamento do jogo
    }

    @Override
    public void dispose() {
        background.dispose(); // Libera a memória da textura de fundo
        textureShip.dispose(); // Libera a memória da textura da nave
        batch.dispose(); // Libera a memória do SpriteBatch
    }
}
