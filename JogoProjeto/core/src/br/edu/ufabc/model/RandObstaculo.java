package br.edu.ufabc.model;

import java.util.Random;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.math.Vector3;
 

public class RandObstaculo {
	
	public GameObject getObstaculo(float z) {
		Random rand = new Random();
		int obs = rand.nextInt(4);
		GameObject objeto;
		Vector3 pos;
		//System.out.println(obs);
		switch(obs) {
			case 0: //rato
				objeto = new GameObject(ModelFactory.getModelbyName("rato1"));//rato com scale do blender
	    		for(Material m : objeto.materials) m.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
	    		pos = new Vector3();
	            pos.x = chosePosition(3);
	            pos.y = -20;
	            pos.z = z;
	            objeto.setTipo('s');
	            objeto.transform.translate(pos);
	            objeto.update(1);
	            return objeto;
			case 1: //pedra1
				objeto = new GameObject(ModelFactory.getModelbyName("pedra_um"));				
	    		for(Material m : objeto.materials) m.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
	    		pos = new Vector3();
	            pos.x = chosePosition(3);
	            pos.y = -20;
	            pos.z = z;
	            objeto.setTipo('s');
	            objeto.transform.translate(pos);
	            objeto.update(1);
	            return objeto;
			case 2: //pedra2
				objeto = new GameObject(ModelFactory.getModelbyName("pedra_dois"));				
	    		for(Material m : objeto.materials) m.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
	    		pos = new Vector3();
	            pos.x = chosePosition(2)+20;
	            objeto.setPos((int) pos.x/45);
	            pos.y = -20;
	            pos.z = z;
	            objeto.setTipo('p');
	            objeto.transform.translate(pos);
	            objeto.update(1);
	            return objeto;
			case 3:
				objeto = new GameObject(ModelFactory.getModelbyName("arvore_um"));
	    		for(Material m : objeto.materials) m.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
	    		pos = new Vector3();
	            pos.x = chosePosition(3);
	            objeto.setPos((int) pos.x/45);
	            pos.y = -20;
	            pos.z = z;
	            objeto.setTipo('a');
	            objeto.transform.translate(pos);
	            objeto.update(1);
	            return objeto;
			case 4:
				objeto = new GameObject(ModelFactory.getModelbyName("cachorro"));
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
				objeto = new GameObject(ModelFactory.getModelbyName("planta"));
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
    	return 45*(rand.nextInt(i)-1);
    }

}
