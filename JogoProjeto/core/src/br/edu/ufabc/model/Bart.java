package br.edu.ufabc.model;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.math.Vector3;

public class Bart {
	private GameObject      estados[];
	private int             estado;
	public static final int IDLE=0;
	
	public Bart() {
		estados = new GameObject[4];
		estados[IDLE] = new GameObject(ModelFactory.getModelbyName("BART"));
		estados[IDLE].transform.scale(20,20,20);
		estados[IDLE].transform.rotate(Vector3.Y, 180);
		for (Material mat: estados[IDLE].materials){
//			m.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
			mat.remove(BlendingAttribute.Type);
		}
		
	}
	public void update(float delta) {
//
		estados[IDLE].update(delta);
		
	}
		

	public void andar() {

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
