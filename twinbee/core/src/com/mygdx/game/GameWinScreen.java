package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameWinScreen extends ApplicationAdapter implements Screen {

    final TwinBeeJogo game;
    private Texture background;
    private Background bk;
    private SpriteBatch batch;
    private Music gameWinMusic;
    private BitmapFont font;

    public GameWinScreen(TwinBeeJogo game) {
        this.game = game;
        batch = new SpriteBatch();
        background = new Texture("background.png");
        bk = new Background(background);
        gameWinMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/vitoria.mp3"));
        gameWinMusic.setVolume(0.2f);
        gameWinMusic.play();

        font = new BitmapFont();
        font.setColor(Color.PINK);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        game.font.draw(batch, "VOCE VENCEU!!!", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2 + 50);
        batch.end();

        if (Gdx.input.justTouched()) {
            gameWinMusic.stop();
            game.setScreen(new MenuScreen(game));
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
        gameWinMusic.dispose();
        font.dispose();
    }

    @Override
    public void show() {
        gameWinMusic.setVolume(0.2f);
        gameWinMusic.play(); // Inicia a reprodução da música
    }
}
