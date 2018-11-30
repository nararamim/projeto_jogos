package br.edu.ufabc.screen;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.UBJsonReader;

import br.edu.ufabc.model.Bart;
import br.edu.ufabc.model.GameObject;
import br.edu.ufabc.model.ModelFactory;
import br.edu.ufabc.util.Parameters;

public class GameScreen extends AbstractScreen{

	private PerspectiveCamera 			camera;
	private Environment      	 		environment;
	private ModelBatch     	  	 	 	modelBatch;
	private ArrayList<ModelInstance>    ceus;
	//private ModelInstance  			 	modelo3D;
	//private CameraInputController	 	inputController;
	
	/////////////////////////////////////////////////
	
	private ArrayList<GameObject>		caminhos;
    private ArrayList<GameObject>       objetos;
	private Bart 						Bart;
	private double						pontos = 0;
	private BitmapFont 					font;
	private SpriteBatch 				spriteBatch;
	private Matrix4 					viewMatrix;
	private int							zcaminho;
	private int							countcaminho;
    private boolean                     trombou;
	
	//////////////////////////////////////////////////

	public GameScreen(String id) {
		super(id);
		modelBatch = new ModelBatch();

        setEnvironment();
        //set array de Caminhos
        caminhos = new ArrayList<GameObject>();
        setCamera();

        ModelLoader<ModelLoader.ModelParameters> loader;
		loader = new G3dModelLoader(new UBJsonReader());

        setCeu(loader);
		
		Bart = new Bart();
        objetos = new ArrayList<GameObject>();

        for (GameObject n : caminhos) {
            GameObject rato = new GameObject(ModelFactory.getModelbyName("rato"));
            Vector3 pos = new Vector3();
            //n.transform.getTranslation(pos);
            rato.transform.translate(0,0,0);
            rato.update(1);
            objetos.add(rato);
        }

        setFonts();

        spriteBatch = new SpriteBatch();
		viewMatrix = new Matrix4();
		zcaminho = -80;
		countcaminho = 0;

        setupAlternanciaCaminhos();
	}

    private void setupAlternanciaCaminhos() {
        for(int i = 0; i < 6; i++) {
            GameObject caminho = new GameObject(ModelFactory.getModelbyName("chao"));
            Vector3 pos = new Vector3();
            pos.x = -188;
            pos.y = 0;
            pos.z = zcaminho;
            zcaminho-=185;
            caminho.transform.translate(pos);
            caminho.update(1);

            caminhos.add(caminho);
        }
    }

    private void setFonts() {
        font = new BitmapFont(Gdx.files.internal("fonts/gameTitle.fnt"));
        font.setColor(Color.YELLOW);
    }

    private void setCeu(ModelLoader<ModelLoader.ModelParameters> loader) {
        Model modeloCeu = loader.loadModel(Gdx.files.internal("sky_ground.g3db"));
        ceus = new ArrayList<ModelInstance>();
        ModelInstance ceu = new ModelInstance(modeloCeu);
        ceu.transform.translate(0, 0, -650);
        ceu.transform.rotate(Vector3.X, 90);
        ceu.transform.rotate(Vector3.Y, 90);
        ceu.transform.translate(0, 0, -520);
        ceu.transform.translate(462, 40, 250);
        ceu.transform.scale(4, 1, 4);
        ceus.add(ceu);
    }

    private void setEnvironment() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
    }

    private void setCamera() {
        camera = new PerspectiveCamera(67.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0f, 55f, 50f);
        camera.lookAt(0f, 40f, 0f);  // a camera "olha" para a origem
        camera.near = 0.1f;
        camera.far  = 800f;
        camera.update();
    }

    @Override
	public void dispose() {
		modelBatch.dispose();
	}

	@Override
	public void update(float delta) {
		countcaminho+=1;
		pontos+=0.03;
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			setDone(true);
		}

        updateCaminho();

        for(GameObject g : caminhos) {
			g.transform.translate(0,0,3);
			g.update(1);
		}		

		// teclas de jogo
		if(Gdx.input.isKeyJustPressed(Keys.UP)) {
			Bart.pular();
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			Bart.direita();
		}
		if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			Bart.esquerda();
		}
		
		Bart.update(delta);
        checkColisions();
	}

    private void updateCaminho() {
        if(countcaminho == 62) {
            GameObject caminho = new GameObject(ModelFactory.getModelbyName("chao"));
            Vector3 pos = new Vector3();
            pos.x = -188;
            pos.y = 0;
            pos.z = -850;
            caminho.transform.translate(pos);
            caminho.update(1);

            caminhos.add(caminho);
            countcaminho = 0;
            caminhos.remove(0);
        }
    }

    @Override
	public void draw(float delta) {
        // TODO Auto-generated method stub
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		
		modelBatch.begin(camera);
		modelBatch.render(Bart.getCurrent(), environment);
		
		for(GameObject g : caminhos) {
			modelBatch.render(g,environment);
		}
		for(ModelInstance m: ceus) {
			modelBatch.render(m, environment);
		}
		modelBatch.end();
		
		camera.update();
		///////////////////////////////////////
		viewMatrix.setToOrtho2D(0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.begin();
		//pontos+=1;
		font.draw(spriteBatch, "Pontos\n" + (int) pontos, 10, 550);
		spriteBatch.end();
		
		///////////////////////////////////////
	}

    public void checkColisions() {
        for (GameObject g : objetos) {
            if (g.collidesWith(Bart.getCurrent())) {
                System.out.println("Bart trombou no objeto");
                trombou = true;
                break;
            } else {
                trombou = false;
            }
        }
    }
}
