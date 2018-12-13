package br.edu.ufabc.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import br.edu.ufabc.screen.AbstractScreen;
import br.edu.ufabc.util.Parameters;

public class CreditsScreen extends AbstractScreen{
	private SpriteBatch     spriteBatch;
	private Texture         texture;
	private Matrix4         viewMatrix;
	private BitmapFont      fontEnd;
	private BitmapFont      fontStart;
	private Music           creditsMusic;
	private double          tempo;
	private boolean         titleVisible = true;

	public CreditsScreen(String id) {
		super(id);
		texture = new Texture(Gdx.files.internal("GameOverScreen.png"));
		spriteBatch = new SpriteBatch();
		viewMatrix  = new Matrix4();
		
		fontStart = new BitmapFont(Gdx.files.internal("fonts/pressStart.fnt"));
		fontStart.setColor(Color.BLACK);
		fontStart.getData().setScale(0.7f, 1f);
		
		fontEnd = new BitmapFont(Gdx.files.internal("fonts/theEnd.fnt"));
		fontEnd.setColor(Color.YELLOW);
		fontEnd.getData().setScale(2.5f, 2f);
		
		musicInitialization();
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		spriteBatch.dispose();
		texture.dispose();
	}

	@Override
	public void update(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			setTryAgain(true);
			setDone(true);			
		}
		else if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			setTryAgain(false);
			setDone(true);
		}

		tempo += Gdx.graphics.getDeltaTime();
	    if (tempo >= 0.30f)  {
		    titleVisible = !titleVisible;
		    tempo = 0;
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
		fontStart.draw(spriteBatch, (titleVisible) ? "Press Enter to Try Again" : " ", 200, 200);
		fontEnd.draw(spriteBatch, "THE END", 10, 50);
		
		spriteBatch.end();
	}

	private void musicInitialization() {
		creditsMusic = Gdx.audio.newMusic(Gdx.files.internal("music/fail.mp3"));		
		creditsMusic.play();
	}
}