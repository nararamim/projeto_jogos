package br.edu.ufabc.model;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.math.Vector3;

public class Bart {
	private GameObject estados[];
	private int estado;
	public static final int RUN = 0;
	public static final int JUMP = 1;
	public static final int DYING = 2;
	private int posicao;
	private int posicao2;
	private boolean endGame = false;
	boolean updatingcolunadir = false;
	boolean updatingcolunaesq = false;
	private float moving = 0;
	private int jumping = 0;
	private boolean sobe = false;
	private boolean change = false;


	public Bart() {
		estados = new GameObject[3];
		estados[RUN] = new GameObject(ModelFactory.getModelbyName("BartRun"));
		estados[JUMP] = new GameObject(ModelFactory.getModelbyName("BartJump"));
		estados[DYING] = new GameObject(ModelFactory.getModelbyName("BartDying"));

		posicao = 0;
		posicao2 =0;

		settings();
	}

	private void settings() {
		for (GameObject estado : estados) {
			estado.transform.scale(1.6f, 1.6f, 1.6f);
			estado.transform.rotate(Vector3.Y, 180);
			estado.transform.translate(0, -20, 15);

			for (Material mat : estado.materials) {
				mat.remove(BlendingAttribute.Type);
			}
		}
	}

	public void update(float delta) {
		estados[estado].update(delta);
		if (estado == JUMP && estados[estado].isDone()) {
			estados[estado].reset();
			estado = RUN;
		} else if (estado == JUMP) {
			if (sobe) {
				jumping++;
				for (GameObject estado : estados)
					estado.transform.translate(0, 0.35f, 0);
				if (jumping > 20)
					sobe = false;
			} else {
				if (jumping >= 0) {
					for (GameObject estado : estados)
						estado.transform.translate(0, -0.35f, 0);
					jumping--;
				}
			}
		}
		if (estado == DYING && estados[estado].isDone()) {
			setEndGame(true);
		}

		if (updatingcolunadir) {
			if (moving < 25) {
				if(moving == 6 && change) {
					posicao2++;
					change = false;
				}
				moving += 0.51f;
				for (GameObject estado : estados)
					estado.transform.translate(-0.51f, 0, 0);
			} else {
				moving = 0;
				updatingcolunadir = false;
			}
		}
		else if (updatingcolunaesq) {
			if (moving < 25) {
				if(moving ==6 && change) {
					posicao2--;
					change = false;
				}
				moving += 0.51f;
				for (GameObject estado : estados)
					estado.transform.translate(0.51f, 0, 0);
			} else {
				moving = 0;
				updatingcolunaesq = false;
			}
		}
	}

	public void pular() {
		if (!updatingcolunadir && !updatingcolunaesq && estado!=JUMP) {
			estado = JUMP;
			sobe = true;
		}
	}

	public void morrer() {
		estado = DYING;
	}

	public void direita() {
		
		if (!updatingcolunadir && !updatingcolunaesq && estado!=JUMP) {

			if (posicao < 1) {
				updatingcolunadir = true;
				estado = JUMP;
				change = true;
				posicao++;
			} else {
				posicao = 1;
			}
		}
		
	}

	public void esquerda() {
		
		if (!updatingcolunadir && !updatingcolunaesq && estado!=JUMP) {

			if (posicao > -1) {
				updatingcolunaesq = true;
				estado = JUMP;
				change = true;
				posicao--;
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

	public int getPosicao() {
		return this.posicao2;
	}
	
	public int getAltura() {
		return this.jumping;
	}
}
