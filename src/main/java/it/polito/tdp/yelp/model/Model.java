package it.polito.tdp.yelp.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	
	private YelpDao dao ;
	private Graph<User, DefaultWeightedEdge> grafo;
	private Map<String, User> idMap;
	
	public Model() {
		dao =new YelpDao();
		idMap = new HashMap<>();
		this.dao.getAllUsers(idMap);
	}
	
	public String creag(int n, int anno) {
		grafo = new SimpleWeightedGraph<User, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getvertici(n, idMap));
		
		for(Coppia c : this.dao.getArchi(anno, idMap)) {
			if(grafo.containsVertex(c.getU1()) && grafo.containsVertex(c.getU2()))
				Graphs.addEdge(grafo, c.getU1(), c.getU2(), c.getPeso());
		}
		
		return "Il grafo creato ha : " +this.grafo.vertexSet().size()+"  " +grafo.edgeSet().size();
	}
	
	
	public List<User> getUtenti(){
		List<User> u =new ArrayList<>(grafo.vertexSet());
		
		Collections.sort(u, new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			} 
		});
		return u;
		}

	public List<Adiacenza> utentiPiuSimili(User u) {
		List<Adiacenza> res = new ArrayList<>();
		int max =0;
		
		for(User v: Graphs.neighborListOf(grafo, u)) {
			int peso =(int) grafo.getEdgeWeight(grafo.getEdge(u, v));
			if(peso>max) {
				res.removeAll(res);
				max = peso;
				res.add(new Adiacenza(v, max));
			}else if (peso == max) {
				res.add(new Adiacenza(v, max));
			}
		}
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	
	private YelpDao dao;
	private Graph<User, DefaultWeightedEdge> grafo;
	private Map<String, User> idMap;
	
	public Model() {
		dao = new YelpDao();
		idMap = new HashMap<>();
		
		this.dao.getAllUsers(idMap);
	}
	
	public String creaGrafo(int n, int anno) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(n, idMap));
		
		for(Coppia c: this.dao.getArchi(anno, idMap)) {
			//fare controllo perche non avevo messo le condizioni dei vertici
			if(grafo.containsVertex(c.getU1()) && grafo.containsVertex(c.getU2()))
				Graphs.addEdgeWithVertices(this.grafo, c.getU1(), c.getU2(), c.getPeso());
		}
		
		
		return "Il grafo creato ha "+this.grafo.vertexSet().size()+" vertici e "
				+this.grafo.edgeSet().size()+"  archi!";
	}
	
	
	//usato per avere lista di vertici !!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public List<User> getUtenti(){
		return new ArrayList<>(this.grafo.vertexSet());
	}
	
	
	public List<Adiacenza> utentiPiuSimili(User u){
		int max =0;
		for(DefaultWeightedEdge d : this.grafo.edgesOf(u)) { // edgeof restitruisce un set di 
																//vertici che toccano quello specifico vertice									
			if((int) this.grafo.getEdgeWeight(d)>max) {
				max = (int) this.grafo.getEdgeWeight(d); //se il peso dellarco è maggiore lo inserisco
			}
		}
		
		List<Adiacenza> result = new ArrayList<>();
		for(DefaultWeightedEdge e : this.grafo.edgesOf(u)) {
			if((int)this.grafo.getEdgeWeight(e) == max) {
				User u2 = Graphs.getOppositeVertex(this.grafo, e, u);
				result.add(new Adiacenza(u2, (int)grafo.getEdgeWeight(e)));
			}
		}
		return result;
		}
	
	*/
}
