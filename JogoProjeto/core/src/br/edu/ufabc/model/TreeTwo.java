package br.edu.ufabc.model;

public class TreeTwo {
	private GameObject      estados[];
	private int             estado;
	public static final int IDLE=0;
	
	public TreeTwo() {
		estados = new GameObject[4];
		estados[IDLE] = new GameObject(ModelFactory.getModelbyName("BART"));
		
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
