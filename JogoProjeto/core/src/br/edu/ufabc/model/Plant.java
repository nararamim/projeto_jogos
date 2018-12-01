package br.edu.ufabc.model;

public class Plant {
	private GameObject      estados[];
	private int             estado;
	public static final int IDLE=0;
	
	public Plant() {
		estados = new GameObject[4];
		estados[IDLE] = new GameObject(ModelFactory.getModelbyName("planta"));
		
	}
	public void update(float delta) {
		
	}

	public int getEstado() {
		return estado;
	}
	public GameObject getCurrent() {
		return estados[estado];
	}
}