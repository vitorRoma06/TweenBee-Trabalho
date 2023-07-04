package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen extends ApplicationAdapter implements Screen {

    final TwinBeeJogo game;
    private Texture background;
    private SpriteBatch batch;
    private Music gameOverMusic;

    public GameOverScreen(TwinBeeJogo game) {
        this.game = game;
        batch = new SpriteBatch();
        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/game-over.mp3"));

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // Define a cor de fundo como preto
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        game.font.draw(batch, "GAME OVER", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2 + 50);
        game.font.draw(batch, "ESC - Voltar ao Menu", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2);
        game.font.draw(batch, "ESPACO - Jogar Novamente", Gdx.graphics.getWidth() / 2 - 110,
                Gdx.graphics.getHeight() / 2 - 50);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            gameOverMusic.stop();
            game.setScreen(new MenuScreen(game));
        }

        if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
            gameOverMusic.stop();
            game.setScreen(new MainGameScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        gameOverMusic.dispose();
    }

    @Override
    public void show() {
        gameOverMusic.setVolume(0.2f);
        gameOverMusic.play(); // Inicia a reprodução da música
    }
}
