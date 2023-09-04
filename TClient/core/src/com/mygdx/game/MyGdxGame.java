package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
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

	private Sound morteAlien;
	private Sound somTiro;

	private Texture background;
    private Texture nave;
    private Texture nave1;
    private Texture nave2;
    private Texture nave3;
    private Texture nave4;
    private Texture tiro1;
    private Texture alien1;
    private Texture alien2;
    private Texture alien3;
    private Texture tiroAlien;

	private Movel aliens[] = new Movel[10];
    private Movel tiros[] = new Movel[10];

	String message;
	String msg;
	int posX = 0, posY = 0;
	ObjectOutputStream output;
	ObjectInputStream input;

	@Override
	public void create() {
		batch = new SpriteBatch();

		tiro1 = new Texture("tiro22.png");
        alien1 = new Texture("alien1.png");
        alien2 = new Texture("alien2.png");
        alien3 = new Texture("alien3.png");
        tiroAlien = new Texture("tiroAlien_1.png");

		Socket socket;
		try {
			socket = new Socket(SERVER_HOST, SERVER_PORT);
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 10; i++) {
            tiros[i] = new Movel(2000,2000,20,20,somTiro);
            aliens[i] = new Movel(2000,2000,20,20,morteAlien);
		 }

	}

	@Override
	public void render() {
		try {
			ScreenUtils.clear(0, 0, 0, 1);
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

			//recebe
			String response = (String) input.readObject();
			String vari[] = response.split(";");

			//pega os aliens e tiros deles
			int j=0;
			for(int i = 0; i<50; i++){
				switch(i%5){
					case 0:
					aliens[j].setPosX(Integer.parseInt(vari[i]));
					break;
					case 1:
					aliens[j].setPosY(Integer.parseInt(vari[i]));
					break;
					case 2:
					aliens[j].setTipo(Integer.parseInt(vari[i]));
					break;
					case 3:
					tiros[j].setPosX(Integer.parseInt(vari[i]));
					break;
					case 4:
					tiros[j].setPosY(Integer.parseInt(vari[i]));
					j++;
					break;
				}
			}

			//pega os players e tiros deles
			for (int i = 0; i < 10; i++){
				if(aliens[i].getTipo() == 0){
					aliens[i].setSprite(alien1);
				}else if(aliens[i].getTipo() == 1){
					aliens[i].setSprite(alien3);
				}else if(aliens[i].getTipo() == 2){
					aliens[i].setSprite(alien2);
				}
				tiros[i].setSprite(tiroAlien);
				aliens[i].draw(batch);
				tiros[i].draw(batch);
			}




			batch.end();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
