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

	     modelos.put("BartRun", loader.loadModel(Gdx.files.internal("Running2.g3db")));
	     modelos.put("BartJump", loader.loadModel(Gdx.files.internal("Jump2.g3db")));
	     modelos.put("BartDying", loader.loadModel(Gdx.files.internal("dying2.g3db")));
	     modelos.put("chao", loader.loadModel(Gdx.files.internal("chao.g3db")));
	     modelos.put("rato", loader.loadModel(Gdx.files.internal("rat.g3db")));
	     modelos.put("pedra_um", loader.loadModel(Gdx.files.internal("rock.g3db")));
	     modelos.put("pedra_dois", loader.loadModel(Gdx.files.internal("big_rock2.g3db")));
	     modelos.put("arvore_um", loader.loadModel(Gdx.files.internal("tree.g3db")));
	     modelos.put("planta", loader.loadModel(Gdx.files.internal("plant.g3db")));
	     modelos.put("cachorro", loader.loadModel(Gdx.files.internal("dog.g3db")));
	     modelos.put("rato1", loader.loadModel(Gdx.files.internal("rat1.g3db")));

	}
	
	public static Model getModelbyName(String name) {
		return modelos.get(name);
	}
	


}