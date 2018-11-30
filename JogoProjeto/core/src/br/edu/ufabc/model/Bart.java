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
	public static final int DYING=2;

	public Bart() {
		estados = new GameObject[2];
		estados[RUN] = new GameObject(ModelFactory.getModelbyName("BartRun"));
		estados[JUMP] = new GameObject(ModelFactory.getModelbyName("BartJump"));
		//estados[DYING] = new GameObject(ModelFactory.getModelbyName("BartDying"));

		settings();
	}

	private void settings(){
	    for(GameObject estado: estados){
            estado.transform.scale(20,20,20);
            estado.transform.rotate(Vector3.Y, 180);
            estado.transform.translate(0,0.5f, 0);

            for (Material mat: estado.materials){
                //mat.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
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
	}
		

	public void pular() {
        //TODO Lógica de pular em parábola
		estado = JUMP;
	}
	
	public void direita() {
        estado = JUMP;
        for (GameObject estado: estados) {
            estado.transform.translate(-1f,0,0);
        }
	}
	public void esquerda() {
        estado = JUMP;
        for (GameObject estado: estados) {
            estado.transform.translate(1f,0,0);
        }
	}

	public int getEstado() {
		return estado;
	}
	public GameObject getCurrent() {
		return estados[estado];
	}

}
