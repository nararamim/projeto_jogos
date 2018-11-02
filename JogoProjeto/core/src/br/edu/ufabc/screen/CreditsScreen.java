package br.edu.ufabc.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import br.edu.ufabc.screen.AbstractScreen;
import br.edu.ufabc.util.Parameters;

public class CreditsScreen extends AbstractScreen{
	private SpriteBatch spriteBatch;
	private Texture     texture;
	private Matrix4 viewMatrix;
	private BitmapFont fontEnd;

	public CreditsScreen(String id) {
		super(id);
		texture = new Texture(Gdx.files.internal("GameOverScreen.png"));
		spriteBatch = new SpriteBatch();
		viewMatrix  = new Matrix4();
		
		fontEnd = new BitmapFont(Gdx.files.internal("fonts/theEnd.fnt"));
		fontEnd.setColor(Color.YELLOW);
		fontEnd.getData().setScale(2.5f, 2f);
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		spriteBatch.dispose();
		texture.dispose();
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			setDone(true);
		}
	}

	@Override
	public void draw(float delta) {
		// TODO Auto-generated method stub
		viewMatrix.setToOrtho2D(0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.begin();
		spriteBatch.draw(texture,0,0,Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT,
                                 0,0,texture.getWidth(), texture.getHeight(),
                                 false, false);
		
		fontEnd.draw(spriteBatch, "THE END", 10, 50);
		
		spriteBatch.end();
	}

}