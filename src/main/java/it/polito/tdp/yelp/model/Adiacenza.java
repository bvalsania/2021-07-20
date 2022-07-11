package it.polito.tdp.yelp.model;

public class Adiacenza {
	
	private User u;
	private Integer grado ;

	public Adiacenza(User u, Integer grado) {
		super();
		this.u = u;
		this.grado = grado;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public Integer getGrado() {
		return grado;
	}

	public void setGrado(Integer grado) {
		this.grado = grado;
	}

	@Override
	public String toString() {
		return " " + u + ", grado=" + grado + "\n";
	}

}
