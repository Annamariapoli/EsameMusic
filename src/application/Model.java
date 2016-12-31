package application;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import bean.ArtistiEAscolti;
import bean.Country;
import db.Dao;

public class Model {
	
	private Dao dao = new Dao();
	private SimpleWeightedGraph<Country, DefaultWeightedEdge>grafo = null;
	
	public List<Integer> mesi (){
		List<Integer > mesi = dao.getMesi();
		return mesi;
	}
	
	public List<ArtistiEAscolti> getArtisti(int mese){
		List<ArtistiEAscolti> art = dao.getArtistiPiuAscoltati(mese);
		return art;
	}
	
	public List<String> getNomiMesi(){                     //funziona
		List<Integer> numMesi = dao.getMesi();
		List<String> nomiMesi= new LinkedList<String>();
		String s1=null;
		String s2=null;
		String s3=null;	
		for(Integer i : numMesi){
			if(i==1){
				 s1 ="Gennaio";
			}
			if(i==11){
				 s2 ="Novembre";
			}
			if(i==12){
				 s3 ="Dicembre";
			}
		}
		nomiMesi.add(s1);
		nomiMesi.add(s2);
		nomiMesi.add(s3);
		return nomiMesi;
	}

	public int getConvertoInNumero(String nomeMese){     //funziona
		int numeroMese=0;
		if(nomeMese.equals("Gennaio")){
			numeroMese =1;
		}
		if(nomeMese.equals("Novembre")){
			numeroMese=11;
		}
		if(nomeMese.equals("Dicembre")){
			numeroMese=12;
		}
		return numeroMese;
	}

	
	public Country getCountry(int id){
		Country  c = dao.getCountryById(id);
		return c;
	}
	
	public List<Integer> getNazioni(int mese){
		List<Integer> nazioni = dao.getNazioniDeiVenti(mese);
		return nazioni;
	}
	
	public boolean artistiInComune(int mese, int id1, int id2){
		return dao.getNazioniAlmenoUnArtistaComune(mese, id1, id2);
	}
	
	public int getNumArt(int mese, Country c1, Country c2){
		int conta = dao.numeroArtisti(mese, c1, c2);
		return conta;
	}
	
	public void buildGraph(int mese){
		grafo = new SimpleWeightedGraph<Country, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		List<Integer> nazioniID = dao.getNazioniDeiVenti(mese);                                             //lista di ID delle nazioni che mi servono
		List<Country> nazioni = new LinkedList<Country>();                                                //creo lista vuota
		for(Integer i : nazioniID){
			Country c = getCountry(i);        //da ID passo a Country
			if(!nazioni.contains(c)){
				nazioni.add(c);
			}
		}
		Graphs.addAllVertices(grafo, nazioni);
		for(Country c1 : grafo.vertexSet()){
			for(Country c2 : grafo.vertexSet()){
				if(artistiInComune(mese, c1.getId(), c2.getId())){    //se in quel mese hanno ascoltato almeno un artista 
					//metto arco e peso
					int numeroArtistiInComune = getNumArt(mese, c1, c2);
					Graphs.addEdge(grafo, c1, c2, numeroArtistiInComune);
					
				}
			}
		}
		System.out.println(grafo.toString());
	}
	
	public static void main(String [] args){
		Model m = new Model();
		m.buildGraph(12);
	}
}
