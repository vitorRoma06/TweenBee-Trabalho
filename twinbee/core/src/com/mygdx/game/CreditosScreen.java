package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class CreditosScreen extends ApplicationAdapter implements Screen {

    final TwinBeeJogo game;

    SpriteBatch batch;


    private String creditsTitle = "Creditos";
    private String namesText = "Alanderson Figueiredo de Lima\nHenrique Giberti Pilo Fernandes\nVitor de Roma Honorio";
    private String referencesTitle = "Referencias";
    private String referencesText = "https://love2d.org/wiki/Free_Game_Resources";

    private Stage stage;
    private TextButton backButton;
    private Skin skin;

    public CreditosScreen(TwinBeeJogo game) {
        this.game = game;
        batch = new SpriteBatch();
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = game.font;

        Texture buttonTexture = new Texture("voltar-left.png");
        buttonStyle.up = new Image(buttonTexture).getDrawable();

        skin.add("default", buttonStyle);

        backButton = new TextButton("", skin);
        backButton.setPosition(20, Gdx.graphics.getHeight() - backButton.getHeight() - 20);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        });

        stage.addActor(backButton);
    }

    @Override
    public void render(float delta) {
        Color backgroundColor = Color.valueOf("#0009AC");
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        game.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        // Desenha o título "Créditos"
        game.font.getData().setScale(2);
        game.font.setColor(1, 1, 1, 1);
        game.font.draw(batch, creditsTitle, 0, Gdx.graphics.getHeight() - 50, Gdx.graphics.getWidth(), Align.center,
                false);

        // Desenha os nomes
        game.font.getData().setScale(1.5f);
        game.font.setColor(1, 1, 1, 1);
        game.font.draw(batch, namesText, 50, Gdx.graphics.getHeight() - 150, Gdx.graphics.getWidth() - 100, Align.left,
                true);

        // Desenha o título "Referências"
        game.font.getData().setScale(2);
        game.font.setColor(1, 1, 1, 1);
        game.font.draw(batch, referencesTitle, 0, Gdx.graphics.getHeight() - 350, Gdx.graphics.getWidth(), Align.center,
                false);

        // Desenha as referências
        game.font.getData().setScale(1.5f);
        game.font.setColor(1, 1, 1, 1);
        game.font.draw(batch, referencesText, 50, Gdx.graphics.getHeight() - 450, Gdx.graphics.getWidth() - 100,
                Align.left, true);
        game.font.getData().setScale(1f);

        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        skin.dispose();
        stage.dispose();
    }

    @Override
    public void hide() {
    }

    // Restante dos métodos omitidos para manter o código conciso
}
