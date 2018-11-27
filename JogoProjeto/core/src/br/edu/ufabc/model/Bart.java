package br.edu.ufabc.model;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.math.Vector3;

public class Bart {
	private GameObject      estados[];
	private int             estado;
	public static final int RUN=0;
	public static final int JUMP=1;
	
	public Bart() {
		estados = new GameObject[2];
		estados[RUN] = new GameObject(ModelFactory.getModelbyName("BartRun"));
		estados[RUN].transform.scale(20,20,20);
		estados[RUN].transform.rotate(Vector3.Y, 180);
		
		estados[JUMP] = new GameObject(ModelFactory.getModelbyName("BartJump"));
		estados[JUMP].transform.scale(20,20,20);
		estados[JUMP].transform.rotate(Vector3.Y, 180);
		
		for (Material mat: estados[RUN].materials){
//			m.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
			mat.remove(BlendingAttribute.Type);
		}
		
	}
	public void update(float delta) {
		estados[estado].update(delta);		
	}
		

	public void pular() {
		for (GameObject estado: estados) {
			estado.transform.translate(200,0,0);
		}
		estado = JUMP;
	}
	
	public void virarEsquerda() {

	}
	public void virarDireita() {

	}

	public int getEstado() {
		return estado;
	}
	public GameObject getCurrent() {
		return estados[estado];
	}

}
