package br.edu.ufabc.screen;

import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen{
	public abstract void   update(float delta);
	public abstract void   draw(float delta);
	private boolean        done; 
	private String         id;
	private boolean        setTryAgain;
	
	public AbstractScreen(String id) {
		this.id = id;
	}
	
	public boolean getTryAgain() {  
		return this.setTryAgain;
	}
	
	public void setTryAgain(boolean t) {
		this.setTryAgain = t;
	}
	
	public String getId() {  
		return this.id;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}
	public boolean isDone() {
		return this.done;
	}
	
	public void render(float delta) {
		update(delta);
		draw(delta);
	}
	public void show() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
