package br.edu.ufabc.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import br.edu.ufabc.util.Parameters;

public class StartScreen extends AbstractScreen {
	private Texture	        texture;
	private SpriteBatch		spriteBatch;
	private Matrix4         viewMatrix;
	private BitmapFont      fontStart;
	private BitmapFont      fontTitle;
	private double          tempo;
	private boolean         titleVisible = true;
	private Music           startMusic;

	public StartScreen(String id) {
		super(id);
		texture = new Texture(Gdx.files.internal("IntroScreen.png"));
		spriteBatch = new SpriteBatch();
		viewMatrix  = new Matrix4();
		
		fontStart = new BitmapFont(Gdx.files.internal("fonts/pressStart.fnt"));
		fontStart.setColor(Color.YELLOW);
		
		fontTitle = new BitmapFont(Gdx.files.internal("fonts/gameTitle.fnt"));
		fontTitle.setColor(Color.YELLOW);
		fontTitle.getData().setScale(1.3f, 1.6f);
		
		musicInitialization();
	}
	@Override
	public void dispose() {
		spriteBatch.dispose();
		texture.dispose();
	}

	@Override
	public void update(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			startMusic.dispose();
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
		viewMatrix.setToOrtho2D(0,0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.begin();
		spriteBatch.draw(texture, 0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT,
				                  0, 0, texture.getWidth(), texture.getHeight(), 
				                  false, false);
		
		fontStart.draw(spriteBatch, (titleVisible) ? "Press Enter" : " ", 430, 80);
		fontTitle.draw(spriteBatch, "Runner", 10, 550);
		fontTitle.draw(spriteBatch, "Bart", 10, 480);
		
		spriteBatch.end();
	}
	
	private void musicInitialization() {
		startMusic = Gdx.audio.newMusic(Gdx.files.internal("music/simpsons_theme.mp3"));
		startMusic.setLooping(true);
		startMusic.play();
	}
}