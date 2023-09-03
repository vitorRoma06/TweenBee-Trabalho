package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyGdxGame extends ApplicationAdapter {

	private static final String SERVER_HOST = "localhost";
	private static final int SERVER_PORT = 6450;

	SpriteBatch batch;
	Texture img;
	String message;
	String msg;
	int posX = 0, posY = 0;
	ObjectOutputStream output;
	ObjectInputStream input;

	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		Socket socket;
		try {
			socket = new Socket(SERVER_HOST, SERVER_PORT);
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void render() {
		try {
			ScreenUtils.clear(1, 0, 0, 1);
			batch.begin();

			// olha qual vai ser a variável
			message = "a;";
			if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				message = "up;";
				System.out.println("mandou cima");
			}
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				message = "left;";
				System.out.println("mandou esquerda");
			}
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				message = "down;";
				System.out.println("mandou baixo");
			}
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				message = "right;";
				System.out.println("mandou direita");
			}

			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
				message = message + "space;";
				System.out.println("mandou espaço");
			}else {
				message = message + "a;";
			}

			// envia
			output.writeObject(message);
			output.flush();

			batch.draw(img, 0, 0);
			batch.end();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
