package it.polito.tdp.yelp.model;

public class Event implements Comparable<Event> {
	
	public enum EventType{
		DA_INTERVISTARE,
		FERIE,
	}

	private int giorno;
	private User intervistato;
	private EventType type;
	private Giornalista giornalista;
	public Event(int giorno, User intervistato, Giornalista giornalista, EventType type) {
		super();
		this.giorno = giorno;
		this.intervistato = intervistato;
		this.giornalista = giornalista;
		this.type = type;
	}
	
	
	public EventType getType() {
		return type;
	}

	public int getGiorno() {
		return giorno;
	}
	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}
	public User getIntervistato() {
		return intervistato;
	}
	public void setIntervistato(User intervistato) {
		this.intervistato = intervistato;
	}
	public Giornalista getGiornalista() {
		return giornalista;
	}
	public void setGiornalista(Giornalista giornalista) {
		this.giornalista = giornalista;
	}
	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return this.giorno-o.giorno;
	}
	

}
