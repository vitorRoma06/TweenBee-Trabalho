package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Timer;

public class MenuScreen extends ApplicationAdapter implements Screen {

    final TwinBeeJogo game;

    private SpriteBatch batch;
    private Sprite twinBeeImage;
    private float twinBeeY;

    // Variáveis para os botões
    private Stage stage;
    private TextButton playButton;
    private TextButton creditsButton;
    private TextButton quitButton;

    // Variável para o som do botão
    private Sound buttonSound;
    private Music starMusic;

    private boolean playButtonClicked;
    private boolean creditsButtonClicked;
    private boolean quitButtonClicked;

    public MenuScreen(TwinBeeJogo game) {
        this.game = game;
        batch = new SpriteBatch();

        starMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/music-start.mp3"));
        starMusic.setLooping(true);
        starMusic.setVolume(0.2f);
        starMusic.play();
    }

    @Override
    public void show() {

        Texture twinBeeTexture = new Texture("twinbee.png");
        twinBeeImage = new Sprite(twinBeeTexture);
        twinBeeImage.setSize(twinBeeImage.getWidth() * 0.3f, twinBeeImage.getHeight() * 0.3f);
        twinBeeY = -twinBeeImage.getHeight();

        // Inicializando os botões
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.font = game.font;

        // Carregando a imagem de fundo personalizado do botão
        Texture buttonTexture = new Texture("button.png");
        buttonStyle.up = new Image(buttonTexture).getDrawable();

        playButton = new TextButton("Jogar", buttonStyle);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - playButton.getHeight() / 2 - 50);

        creditsButton = new TextButton("Creditos", buttonStyle);
        creditsButton.setPosition(Gdx.graphics.getWidth() / 2 - creditsButton.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - creditsButton.getHeight() / 2 - 100);

        quitButton = new TextButton("Sair", buttonStyle);
        quitButton.setPosition(Gdx.graphics.getWidth() / 2 - quitButton.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - quitButton.getHeight() / 2 - 150);

        stage.addActor(playButton);
        stage.addActor(creditsButton);
        stage.addActor(quitButton);

        // Carregando o som do botão
        buttonSound = Gdx.audio.newSound(Gdx.files.internal("button-press1.wav"));

        playButtonClicked = false;
        creditsButtonClicked = false;
        quitButtonClicked = false;
    }

    @Override
    public void render(float delta) {
        Color backgroundColor = Color.valueOf("#0009AC");
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (twinBeeY < Gdx.graphics.getHeight() / 2 - twinBeeImage.getHeight() / 2) {
            twinBeeY += 1f; // Ajuste a velocidade de movimento aqui (1f = 1 pixel por frame)
            twinBeeImage.setY(twinBeeY);

            // Atualize as posições dos botões junto com o deslocamento do TwinBee
            playButton.setPosition(Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2,
                    twinBeeY + Gdx.graphics.getHeight() / 2 - playButton.getHeight() / 2 - 350);

            creditsButton.setPosition(Gdx.graphics.getWidth() / 2 - creditsButton.getWidth() / 2,
                    twinBeeY + Gdx.graphics.getHeight() / 2 - creditsButton.getHeight() / 2 - 400);

            quitButton.setPosition(Gdx.graphics.getWidth() / 2 - quitButton.getWidth() / 2,
                    twinBeeY + Gdx.graphics.getHeight() / 2 - quitButton.getHeight() / 2 - 450);
        }

        // Verificando se os botões foram pressionados
        stage.act(delta);

        if (playButton.isPressed() && !playButtonClicked) {
            playButtonClicked = true;
            buttonSound.play(); // Reproduzindo o som do botão

            // Adicionando um atraso de 0.7 segundos antes de iniciar o jogo
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    game.setScreen(new MainGameScreen(game));
                    dispose();
                }
            }, 0.2f); // Atraso de 0.7 segundos (em segundos)
        }

        if (creditsButton.isPressed() && !creditsButtonClicked) {
            creditsButtonClicked = true;
            buttonSound.play(); // Reproduzindo o som do botão

            // Adicionando um atraso de 0.7 segundos antes de mostrar os créditos
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    // Implemente o código para exibir a tela de créditos aqui
                    game.setScreen(new CreditosScreen(game));
                    dispose();
                }
            }, 0.2f); // Atraso de 0.7 segundos (em segundos)
        }

        if (quitButton.isPressed() && !quitButtonClicked) {
            quitButtonClicked = true;
            buttonSound.play(); // Reproduzindo o som do botão

            // Adicionando um atraso de 0.7 segundos antes de sair do jogo
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    Gdx.app.exit();
                }
            }, 0.2f); // Atraso de 0.7 segundos (em segundos)
        }

        stage.draw();
        batch.begin();
        batch.draw(twinBeeImage, Gdx.graphics.getWidth() / 2 - twinBeeImage.getWidth() / 2 - 140, twinBeeY);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        twinBeeImage.getTexture().dispose();
        stage.dispose();
        buttonSound.dispose();
        starMusic.dispose();
    }

    @Override
    public void hide() {
        // Método vazio
    }

    // Implementação dos métodos não utilizados omitida
}
