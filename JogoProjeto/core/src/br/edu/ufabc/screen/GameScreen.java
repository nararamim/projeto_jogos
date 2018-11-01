package br.edu.ufabc.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import br.edu.ufabc.util.Parameters;

public class GameScreen extends AbstractScreen{
	private SpriteBatch spriteBatch;
	private Texture     texture;
	private Matrix4 viewMatrix;
	private Matrix4 tranMatrix;

	public GameScreen(String id) {
		super(id);
		texture = new Texture(Gdx.files.internal("badlogic.jpg"));
		spriteBatch = new SpriteBatch();
		viewMatrix  = new Matrix4();
		tranMatrix  = new Matrix4();
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
		spriteBatch.setTransformMatrix(tranMatrix);
		spriteBatch.begin();
		spriteBatch.draw(texture,0,0,Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT,
                                 0,0,texture.getWidth(), texture.getHeight(),
                                 false, false);
		spriteBatch.end();

		


	}

}
