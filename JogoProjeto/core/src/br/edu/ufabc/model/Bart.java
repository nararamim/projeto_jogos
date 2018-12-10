package br.edu.ufabc.model;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.math.Vector3;

public class Bart {
	private GameObject estados[];
	private int estado;
	public static final int RUN = 0;
	public static final int JUMP = 1;
	public static final int DYING = 2;
	private float posicao;
	private boolean endGame = false;
	boolean updatingcolunadir = false;
	boolean updatingcolunaesq = false;
	private float moving = 0;

	public Bart() {
		estados = new GameObject[3];
		estados[RUN] = new GameObject(ModelFactory.getModelbyName("BartRun"));
		estados[JUMP] = new GameObject(ModelFactory.getModelbyName("BartJump"));
		estados[DYING] = new GameObject(ModelFactory.getModelbyName("BartDying"));

		posicao = 0;

		settings();
	}

	private void settings() {
		for (GameObject estado : estados) {
			//estado.transform.scale(20, 20, 20);
			estado.transform.rotate(Vector3.Y, 180);
			estado.transform.translate(0, 0.5f, 0);

			for (Material mat : estado.materials) {
				// mat.set(new BlendingAttribute(GL20.GL_SRC_ALPHA,
				// GL20.GL_ONE_MINUS_SRC_ALPHA));
				mat.remove(BlendingAttribute.Type);
			}
		}
	}

	public void update(float delta) {
		estados[estado].update(delta);
		if (estado == JUMP && estados[estado].isDone()) {
			estados[estado].reset();
			estado = RUN;
		}

		if (estado == DYING && estados[estado].isDone()) {
			setEndGame(true);
		}

		if (updatingcolunadir) {
			if (moving < 20) {
				moving += 0.5f;
				for (GameObject estado : estados)
					estado.transform.translate(-0.5f, 0, 0);
			} else {
				moving = 0;
				updatingcolunadir = false;
			}
		}
		if (updatingcolunaesq) {
			if (moving < 20) {
				moving += 0.5f;
				for (GameObject estado : estados)
					estado.transform.translate(0.5f, 0, 0);
			} else {
				moving = 0;
				updatingcolunaesq = false;
			}
		}
	}

	public void pular() {
		// TODO Lógica de pular em parábola
		estado = JUMP;
	}

	public void morrer() {
		estado = DYING;
	}

	public void direita() {
		
		if (!updatingcolunadir && !updatingcolunaesq && estado!=JUMP) {

			if (posicao < 1) {
				updatingcolunadir = true;
//        	float b = 0;
//        	while (b>=-1)
//	        for (GameObject estado: estados) {
//	            estado.transform.translate(-0.05f,0,0);
//	            b+=0.05;
//	        }
				posicao++;
				estado = JUMP;
			} else {
				posicao = 1;
			}
		}
		
	}

	public void esquerda() {
		
		if (!updatingcolunadir && !updatingcolunaesq && estado!=JUMP) {

			if (posicao > -1) {
				updatingcolunaesq = true;
//			for (GameObject estado : estados) {
//				estado.transform.translate(1f, 0, 0);
//			}
				posicao--;
				estado = JUMP;
			} else {
				posicao = -1;
			}
		}
		
	}

	public int getEstado() {
		return estado;
	}

	public GameObject getCurrent() {
		return estados[estado];
	}

	public boolean getEndGame() {
		return this.endGame;
	}

	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}

}
