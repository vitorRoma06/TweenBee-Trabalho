package com.twinbee.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class TwinBeeGame extends ApplicationAdapter {
	SpriteBatch batch;
	//criação do fundo do tipo Texture
	Texture background;
	
	@Override
	//é importante lembrar que o create é como se fosse o construtor
	public void create () {
		batch = new SpriteBatch();
	}

	public void execute(){
		
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
