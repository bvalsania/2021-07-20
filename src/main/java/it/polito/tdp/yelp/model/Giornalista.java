package it.polito.tdp.yelp.model;

public class Giornalista {

	private int id;
	private int numIntervistati;
	public Giornalista(int id) {
		super();
		this.id = id;
		this.numIntervistati = 0;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumIntervistati() {
		return numIntervistati;
	}
	public void setNumIntervistati(int numIntervistati) {
		this.numIntervistati = numIntervistati;
	}
	
	public void incrementaI() {
		this.numIntervistati++;
	}
}
