package br.edu.ufabc.model;

import java.util.Random;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.math.Vector3;
 

public class RandObstaculo {
	
	public GameObject getObstaculo(float z) {
		Random rand = new Random();
		int obs = rand.nextInt(2); //eu usaria 4
		GameObject objeto;
		Vector3 pos;
		//System.out.println(obs);
		switch(obs) {
			case 0: //rato
				objeto = new GameObject(ModelFactory.getModelbyName("rato1"));//rato com scale do blender
				objeto.setResize(0.55f);
	    		for(Material m : objeto.materials) m.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
	    		pos = new Vector3();
	            pos.x = chosePosition(3);
	            pos.y = -20;
	            pos.z = z;
	            objeto.transform.translate(pos);
	            objeto.update(1);
	            return objeto;
			case 1: //pedra1
				objeto = new GameObject(ModelFactory.getModelbyName("pedra_um"));
				objeto.setResize(0.28f);
	    		for(Material m : objeto.materials) m.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
	    		pos = new Vector3();
	            pos.x = chosePosition(3);
	            pos.y = -20;
	            pos.z = z;
	            objeto.transform.translate(pos);
	            objeto.transform.scale(0.28f, 0.28f, 0.28f);
	            objeto.update(1);
	            return objeto;
			case 2: //pedra2
				objeto = new GameObject(ModelFactory.getModelbyName("pedra_dois"));
				objeto.setResize(0.3f);
	    		for(Material m : objeto.materials) m.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
	    		pos = new Vector3();
	            pos.x = chosePosition(2)+20;
	            pos.y = -20;
	            pos.z = z;
	            objeto.transform.translate(pos);
	            objeto.transform.scale(0.3f, 0.4f, 0.3f);
	            objeto.update(1);
	            return objeto;
			case 3:
				objeto = new GameObject(ModelFactory.getModelbyName("arvore_um"));
				objeto.setResize(0.8f);
	    		for(Material m : objeto.materials) m.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
	    		pos = new Vector3();
	            pos.x = chosePosition(3);
	            pos.y = -20;
	            pos.z = z;
	            objeto.transform.translate(pos);
	            objeto.transform.scale(0.8f, 0.8f, 0.8f);
	            objeto.update(1);
	            return objeto;
			case 4:
				objeto = new GameObject(ModelFactory.getModelbyName("cachorro"));
				objeto.setResize(0.8f);
	    		for(Material m : objeto.materials) m.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
	    		pos = new Vector3();
	            pos.x = chosePosition(3);
	            pos.y = -25;
	            pos.z = z;
	            objeto.transform.translate(pos);
	            objeto.transform.scale(0.8f, 0.8f, 0.8f);
	            objeto.update(1);
	            return objeto;
			case 5:
				objeto = new GameObject(ModelFactory.getModelbyName("monstro"));
				objeto.setResize(0.15f);
	    		for(Material m : objeto.materials) m.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
	    		pos = new Vector3();
	            pos.x = chosePosition(3);
	            pos.y = -20;
	            pos.z = z;
	            objeto.transform.translate(pos);
	            objeto.transform.scale(0.15f, 0.15f, 0.15f);
	            objeto.update(1);
	            return objeto;
			case 6:
				objeto = new GameObject(ModelFactory.getModelbyName("planta"));
				objeto.setResize(0.8f);
	    		for(Material m : objeto.materials) m.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
	    		pos = new Vector3();
	            pos.x = chosePosition(3);
	            pos.y = -25;
	            pos.z = z;
	            objeto.transform.translate(pos);
	            objeto.transform.scale(0.8f, 0.8f, 0.8f);
	            objeto.update(1);
	            return objeto;
			default:
				objeto = new GameObject(ModelFactory.getModelbyName("rato1"));
	    		for(Material m : objeto.materials) m.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
	    		pos = new Vector3();
	            pos.x = chosePosition(3);
	            pos.y = -20;
	            pos.z = z;
	            objeto.transform.translate(pos);
	            objeto.update(1);
	            return objeto;
		}		
	}
	
    private int chosePosition (int i) {
    	Random rand = new Random();
    	return 35*(rand.nextInt(i)-1);
    }

}
