package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen extends ApplicationAdapter implements Screen {

    final TwinBeeJogo game;
    private Texture background;
    private Background bk;
    private SpriteBatch batch;
    private Music gameOverMusic;

    public GameOverScreen(TwinBeeJogo game) {
        this.game = game;
        batch = new SpriteBatch();
        background = new Texture("background.png");
        bk = new Background(background);
        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/game-over.mp3"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        bk.draw(batch);
        bk.run();
        batch.end();
        
        if (Gdx.input.justTouched()) {
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
        gameOverMusic.dispose();
    }

    @Override
    public void show() {
        gameOverMusic.setVolume(0.2f);
        gameOverMusic.play(); // Inicia a reprodução da música
    }
}
