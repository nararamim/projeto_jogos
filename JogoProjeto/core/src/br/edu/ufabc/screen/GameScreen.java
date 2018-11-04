package br.edu.ufabc.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.UBJsonReader;

import br.edu.ufabc.util.Parameters;

public class GameScreen extends AbstractScreen{
	private PerspectiveCamera camera;
	private Environment       environment;
	private ModelBatch        modelBatch;
	private ModelInstance     modelo3D;
	private CameraInputController inputController;

	public GameScreen(String id) {
		super(id);
		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		camera = new PerspectiveCamera(67.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(10f, 10f, 10f);
		camera.lookAt(0f, 0f, 0f);  // a camera "olha" para a origem
		camera.near = 0.1f;
		camera.far  = 100f;
		camera.update();
		inputController = new CameraInputController(camera);
		Gdx.input.setInputProcessor(inputController);
		
		ModelLoader<ModelLoader.ModelParameters> loader;
		   loader = new G3dModelLoader(new UBJsonReader());
		   
		modelo3D = new ModelInstance(loader.loadModel(Gdx.files.internal("teste.g3db")));
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		modelBatch.dispose();
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			setDone(true);
		}
	}

	@Override
	public void draw(float delta) {
        // TODO Auto-generated method stub
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		
		modelBatch.begin(camera);
		modelBatch.render(modelo3D, environment);
		modelBatch.end();
		
		camera.update();
	}
}
