package br.edu.ufabc.screen;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.audio.Music;
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
	private double						vidas = 3;
	private BitmapFont 					font;
	private SpriteBatch 				spriteBatch;
	private Matrix4 					viewMatrix;
	private float						countcaminho;
    private boolean                     trombou;
    private float						speed = 3f;
    private Music           			gameMusic;
    private float 						countobstaculo;
    
	//////////////////////////////////////////////////

	public GameScreen(String id, int maxScore) {
		super(id);
		
		ModelLoader<ModelLoader.ModelParameters> loader;
		modelBatch = 	new ModelBatch();
		caminhos = 		new ArrayList<GameObject>();
		loader = 		new G3dModelLoader(new UBJsonReader());
		Bart = 			new Bart();
        objetos = 		new ArrayList<GameObject>();
        spriteBatch = 	new SpriteBatch();
		viewMatrix = 	new Matrix4();
		countcaminho = 	0;
		countobstaculo= 0;
        
        setEnvironment();
        setCamera();
        setCeu(loader);
        setFonts();
        setMaxScore(maxScore);
        setupAlternanciaCaminhos();
        addObstaculo();
        musicInitialization();
		        
        
        // TODO: adicionar demais objetos
        /*for (int i = 0 ; i < 20; i ++) {
            GameObject objeto = new GameObject(ModelFactory.getModelbyName("rato"));
            Vector3 pos = new Vector3();
            pos.x = 0;
            pos.y = 0;
            pos.z = zobj;
            zobj-=190;
            objeto.transform.translate(pos);
            //objeto.transform.scale(0.01f, 0.01f, 0.01f );
            objeto.update(1);
            objetos.add(objeto);
        }		*/
	}

    @Override
	public void dispose() {
		modelBatch.dispose();
	}

	@Override
	public void update(float delta) {
		countcaminho+=speed;
		countobstaculo+=speed;
		pontos+=0.01*speed;
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			if ((int) pontos > getMaxScore()) {
				setMaxScore((int)pontos);
			}		
			gameMusic.dispose();
			setDone(true);
		}
		if (vidas > 0) {
	        updateCaminho();	
	        for(GameObject g : caminhos) {	        	
	        	g.transform.translate(0,0,speed);
				g.update(1);        	
			}
			updateObstaculos();
	        for(GameObject h: objetos) {
	        	System.out.println(objetos.size());
	        	h.transform.translate(0,0,speed);
	        	h.update(1);
	        }	        
		}
		else {
			if ((int) pontos > getMaxScore()) setMaxScore((int)pontos);
			Bart.morrer();
			Bart.update(delta);
			gameMusic.dispose();
			setDone(true);
		}

		// teclas de jogo
		if(Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isButtonPressed(Buttons.MIDDLE)) Bart.pular();
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT) || Gdx.input.isButtonPressed(Buttons.RIGHT)) Bart.direita();
		if(Gdx.input.isKeyJustPressed(Keys.LEFT) || Gdx.input.isButtonPressed(Buttons.LEFT)) Bart.esquerda();
        if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
        	vidas--;
            Bart.morrer();
        }		
		Bart.update(delta);
        checkColisions();
	}

    @Override
	public void draw(float delta) {
        // TODO Auto-generated method stub
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		
		modelBatch.begin(camera);
		modelBatch.render(Bart.getCurrent(), environment);
		
		for(GameObject g : caminhos) modelBatch.render(g,environment);
		for(GameObject h : objetos) modelBatch.render(h,environment);
		for(ModelInstance m: ceus) modelBatch.render(m, environment);
		
		
		modelBatch.end();
		
		camera.update();
		///////////////////////////////////////
		viewMatrix.setToOrtho2D(0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.begin();
		//pontos+=1;
		font.draw(spriteBatch, "Score\n" + (int) pontos, 10, 550);
		font.draw(spriteBatch, "MaxScore\n" + (int) getMaxScore(), 330, 550);
		font.draw(spriteBatch, "Lifes\n" + (int) vidas, 670, 550);
		spriteBatch.end();
		
		///////////////////////////////////////
	}

    public void checkColisions() {
        for (GameObject g : objetos) {
            if (g.collidesWith(Bart.getCurrent())) {
                //System.out.println("Bart trombou no objeto");
                trombou = true;
                //vidas--;
                break;
            } else {
                trombou = false;
            }
        }
    }

    private void setupAlternanciaCaminhos() {
		int zcaminho = 		-80;
        for(int i = 0; i < 5; i++) {
            GameObject caminho = new GameObject(ModelFactory.getModelbyName("chao"));
            Vector3 pos = new Vector3();
            pos.x = -188;
            pos.y = 0;
            pos.z = zcaminho;
            zcaminho-=190;
            caminho.transform.translate(pos);
            caminho.update(1);

            caminhos.add(caminho);
        }
    }
    
    private void addObstaculo() {
        //int zobj = 100;
        int zobj = -80;
        for (int i = 0 ; i < 3; i ++) {
            GameObject objeto = new GameObject(ModelFactory.getModelbyName("rato"));
            Vector3 pos = new Vector3();
            pos.x = 0;
            pos.y = 0;
            pos.z = zobj;
            zobj-=190;
            objeto.transform.translate(pos);
            objeto.transform.scale(0.01f, 0.01f, 0.01f );
            objeto.update(1);
            objetos.add(objeto);
        }		
    }
    
    private void musicInitialization() {
    	gameMusic = Gdx.audio.newMusic(Gdx.files.internal("music/simpsons_game_theme.mp3"));
    	gameMusic.setVolume(0.5f);
    	gameMusic.setLooping(true);
    	gameMusic.play();
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
    
    private void updateCaminho() {
        if(countcaminho >= 190) {
            GameObject caminho = new GameObject(ModelFactory.getModelbyName("chao"));
            Vector3 pos = new Vector3();
            pos.x = -188;
            pos.y = 0;
            pos.z = -850;
            caminho.transform.translate(pos);
            caminho.update(1);
            caminhos.add(caminho);
            countcaminho-= 190;
            caminhos.remove(0);
        }
        if(speed < 6.5) speed+=0.0005;
    }
    
    private void updateObstaculos() {
    	if(countobstaculo >= 190) {
    		GameObject objeto = new GameObject(ModelFactory.getModelbyName("rato"));
    		Vector3 pos = new Vector3();
            pos.x = 0;
            pos.y = 0;
            pos.z = -600;
            objeto.transform.translate(pos);
            objeto.transform.scale(0.01f, 0.01f, 0.01f );
            objeto.update(1);
            objetos.add(objeto);
            countobstaculo-=190;
            objetos.remove(0);    		
    	}
    }
}