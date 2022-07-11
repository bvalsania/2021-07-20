package it.polito.tdp.yelp.model;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.yelp.model.Event.EventType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;



public class Simulator {

	//dati in ingresso
	private int x1;
	private int x2;
	private Graph<User,DefaultWeightedEdge> grafo;
	
	
	//dati in uscita
	private int giorni ;
		//i giornalisti sono rappre da un numero tra 1 e x1
	private List<Giornalista> giornalisti;
	
	//modello del mondo ---> per fotografare il sistema
	private Set<User> intervistati;
	
	//coda degli eventi
	private PriorityQueue<Event> queue;
	
	public  Simulator(Graph<User,DefaultWeightedEdge> grafo) {
		this.grafo= grafo;
		
	}
	public void init(int x1, int x2) {
		this.x1 = x1;
		this.x2=x2;
		
		this.intervistati = new HashSet<User>();
		this.giorni=0;
		this.giornalisti = new ArrayList<>();
		for(int id =0;id<this.x1;id++) {
			this.giornalisti.add(new Giornalista(id));
		}
		//pre carico la coda
		for(Giornalista g: this.giornalisti) {
			User inter = selezionaIntervistato(this.grafo.vertexSet());
			this.intervistati.add(inter);
			g.incrementaI();
			
			this.queue.add(new Event(1,inter, g, EventType.DA_INTERVISTARE));
		}
		
	}
	
	public void run() {
		
		while(this.queue.isEmpty() && this.intervistati.size()<x2) {//condizione di terminazione
			Event e = this.queue.poll();
			this.giorni = e.getGiorno();
			
			processEvent(e);
		}
		
	}
	

	private void processEvent(Event e) {

		switch(e.getType()) {
		case DA_INTERVISTARE:
			
			double caso = Math.random();
			if(caso<0.6) {
				//caso 1
				User vicino = selezionaAdiacenti(e.getIntervistato());
				if(vicino == null) {
					vicino = selezionaIntervistato(this.grafo.vertexSet());
				}
				
				this.queue.add(new Event(e.getGiorno()+1, vicino, e.getGiornalista(), EventType.DA_INTERVISTARE));
				
				this.intervistati.add(vicino);
				e.getGiornalista().incrementaI();
				
				
			}else if (caso<0.8) {
				//caso 2
				this.queue.add(new Event(e.getGiorno()+1, e.getIntervistato(), e.getGiornalista(), EventType.FERIE));
			}else {
				//caso 3: domani continuo con lo stesso
				this.queue.add(new Event(e.getGiorno()+1, e.getIntervistato(), e.getGiornalista(), EventType.DA_INTERVISTARE));
			}
				
			
			
			break;
		case FERIE:
			break;
		}
	}
	//setto valori in uscita
	//getto i valori in entrata
	public Set<User> getIntervistati() {
		return intervistati;
	}
	public void setIntervistati(Set<User> intervistati) {
		this.intervistati = intervistati;
	}
	public int getIntervistatori() {
		return x1;
	}
	public int getUtenti() {
		return x2;
	}
	public int getGiorni() {
		return giorni;
	}
	public List<Giornalista> getGiornalisti() {
		return giornalisti;
	}
	public void setIntervistatori(int intervistatori) {
		this.x1 = intervistatori;
	}
	public void setUtenti(int utenti) {
		this.x2 = utenti;
	}
	
	//seleziona un intervistato da lista specificata evitando di sel coloro che sono gia intervistati
	private User selezionaIntervistato(Collection<User> lista) {
		Set <User> candidati = new HashSet<User>(lista);
		candidati.removeAll(this.intervistati);
		
		int scelto =(int)(Math.random()*candidati.size());
		
		return (new ArrayList<User>(candidati)).get(scelto);
	}
	
	private User selezionaAdiacenti(User u) {
		List<User> vicini = Graphs.neighborListOf(grafo, u);
		vicini.removeAll(intervistati);
		
		double max =0;
		
		if(vicini.size()==0) {
			return null;
		}
		for(User v : vicini) {
			double peso = (grafo.getEdgeWeight(this.grafo.getEdge(u, v)));
			if(peso>max) {
				max = peso;
			}
		}
		
		List<User> migliori = new ArrayList<>();
		for(User v : vicini) {
			double peso = (grafo.getEdgeWeight(this.grafo.getEdge(u, v)));
			if(peso==max) {
				migliori.add(v);
			}
		}
		int scelto =(int) (Math.random()*migliori.size());
		return migliori.get(scelto);
	}
}
