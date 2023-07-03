package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Timer;

public class GameScreen extends ApplicationAdapter implements Screen {

    final TwinBeeJogo game;

    private SpriteBatch batch;
    private Texture background;
    private Background bk;

    // Variáveis para a tela de menu
    private BitmapFont font;
    private String titleText = "TwinBee";

    // Variáveis para os botões
    private Stage stage;
    private TextButton playButton;
    private TextButton creditsButton;
    private TextButton quitButton;

    // Variável para o som do botão
    private Sound buttonSound;

    private boolean playButtonClicked;
    private boolean creditsButtonClicked;
    private boolean quitButtonClicked;

    public GameScreen(TwinBeeJogo game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture("background.png");
        // Inicializando a fonte para a tela de menu
        bk = new Background(background);

        // Inicializando os botões
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.font = font;

        // Carregando a imagem de fundo personalizado do botão
        Texture buttonTexture = new Texture("button.png");
        buttonStyle.up = new Image(buttonTexture).getDrawable();

        playButton = new TextButton("Jogar", buttonStyle);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - playButton.getHeight() / 2 - 50);

        creditsButton = new TextButton("Créditos", buttonStyle);
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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Renderizando a tela de menu
        batch.begin();
        bk.draw(batch);
        bk.run();
        font.draw(batch, titleText, Gdx.graphics.getWidth() / 2 - font.getXHeight() * titleText.length() / 2,
                Gdx.graphics.getHeight() / 2 + font.getXHeight());

        batch.end();

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
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        font.dispose();
        stage.dispose();
        buttonSound.dispose();
    }

    @Override
    public void hide() {
        // Método vazio
    }

    // Implementação dos métodos não utilizados omitida
}
