package br.edu.ufabc.model;

public class Bart {
	private GameObject      estados[];
	private int             estado;
	public static final int IDLE=0;
	
	public Bart() {
		estados = new GameObject[4];
		estados[IDLE] = new GameObject(ModelFactory.getModelbyName("BART"));
		
	}
	public void update(float delta) {
		
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
