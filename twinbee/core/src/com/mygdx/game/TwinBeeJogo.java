package com.mygdx.game;
import com.badlogic.gdx.Game;


public class TwinBeeJogo extends Game{
	public void create() {
		this.setScreen(new GameScreen(this));
	}
}