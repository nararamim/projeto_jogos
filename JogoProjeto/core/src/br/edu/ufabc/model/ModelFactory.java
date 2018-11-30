package br.edu.ufabc.model;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.assets.loaders.ModelLoader.ModelParameters;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.utils.UBJsonReader;

public class ModelFactory {
	
	private static HashMap<String, Model> modelos = new HashMap<String,Model>();
	
	static {
		ModelLoader<ModelParameters> loader;
		loader = new G3dModelLoader(new UBJsonReader());
		//System.out.println("Carregando modelos...");
	     modelos.put("BartRun", loader.loadModel(Gdx.files.internal("Running.g3db")));
	     modelos.put("BartJump", loader.loadModel(Gdx.files.internal("Jump.g3db")));
	     modelos.put("BartDying", loader.loadModel(Gdx.files.internal("Jump.g3db")));
	     modelos.put("chao", loader.loadModel(Gdx.files.internal("chao.g3db")));
	     modelos.put("rato", loader.loadModel(Gdx.files.internal("rat.g3db")));
	     modelos.put("pedra", loader.loadModel(Gdx.files.internal("rat.g3db")));

	     //System.out.println("Modelos carregados!");
	}
	
	public static Model getModelbyName(String name) {
		return modelos.get(name);
	}

}