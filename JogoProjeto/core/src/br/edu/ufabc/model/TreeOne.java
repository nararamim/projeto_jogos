package br.edu.ufabc.model;

public class TreeOne {
	private GameObject      estados[];
	private int             estado;
	public static final int IDLE=0;
	
	public TreeOne() {
		estados = new GameObject[4];
		estados[IDLE] = new GameObject(ModelFactory.getModelbyName("arvore_um"));
		
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
