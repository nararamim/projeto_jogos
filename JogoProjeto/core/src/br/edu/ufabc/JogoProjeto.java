package br.edu.ufabc;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.edu.ufabc.screen.StartScreen;
import br.edu.ufabc.screen.CreditsScreen;
import br.edu.ufabc.screen.GameScreen;
import br.edu.ufabc.screen.AbstractScreen;

public class JogoProjeto extends ApplicationAdapter {
	SpriteBatch            batch;
	Texture                img;
	private AbstractScreen currentScreen;
	private int            maxScore = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		currentScreen = new StartScreen("START");
	}

	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
		
		currentScreen.render(Gdx.graphics.getDeltaTime());
		if (currentScreen.isDone()) {
			if (currentScreen.getId().equals("START")) {
				currentScreen = new GameScreen("GAME", 0);
			}
			else if (currentScreen.getId().equals("GAME")) {
				maxScore = currentScreen.getMaxScore();
				currentScreen = new CreditsScreen("CREDITS");
			}
			else if (currentScreen.getId().equals("CREDITS")) {
				if (currentScreen.getTryAgain()) {
					currentScreen = new GameScreen("GAME", maxScore);
				}
				else {
					currentScreen = new StartScreen("START");
				}
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
