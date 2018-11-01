package br.edu.ufabc.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import br.edu.ufabc.util.Parameters;

public class StartScreen extends AbstractScreen {
	private Texture	texture;
	private SpriteBatch spriteBatch;
	private Matrix4     viewMatrix;


	public StartScreen(String id) {
		super(id);
		texture = new Texture(Gdx.files.internal("IntroScreen.png"));
		spriteBatch = new SpriteBatch();
		viewMatrix  = new Matrix4();
	}
	@Override
	public void dispose() {
		spriteBatch.dispose();
		texture.dispose();
	}

	@Override
	public void update(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			setDone(true);
		}
	}

	@Override
	public void draw(float delta) {
		viewMatrix.setToOrtho2D(0,0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.begin();
		spriteBatch.draw(texture, 0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT,
				                  0, 0, texture.getWidth(), texture.getHeight(), 
				                  false, false);
		spriteBatch.end();
	}

}