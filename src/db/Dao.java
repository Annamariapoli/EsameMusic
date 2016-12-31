package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.ArtistiEAscolti;
import bean.Country;

public class Dao {

	public List<Integer> getMesi(){
		Connection conn = DBConnect.getConnection();
		List<Integer> mesi = new LinkedList<Integer>();
		String query = "select distinct month from listening";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()){
				mesi.add(res.getInt("month"));
				
			}
			conn.close();
			return mesi;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}
	
	 public List<ArtistiEAscolti> getArtistiPiuAscoltati(int mese){
		Connection conn = DBConnect.getConnection();
		List<ArtistiEAscolti> artisti = new LinkedList<>();
		String query = "select a.id, a.artist,  count(l.id) as numascolti from listening l, artist a "
				+ "where l.month=12  and a.id=l.artistid group by l.artistid order by numascolti DESC Limit 20";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, mese);
			ResultSet res = st.executeQuery();
			while(res.next()){
				ArtistiEAscolti a = new ArtistiEAscolti(res.getInt("artistid"), res.getString("nome"), res.getInt("numascolti"));
				artisti.add(a);	
			}
			conn.close();
			return artisti;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}	
	}
	
	
	public List<Integer> getNazioniDeiVenti(int mese){                             //prendo dai 20 artisti piu ascoltati le nazioni
		Connection conn = DBConnect.getConnection();
		String query = "select  count(l.id) as numascolti , l.countryid "
				+ "from listening l, artist a  "
				+ "where l.month=12  and a.id=l.artistid  "
				+ "group by l.artistid  "
				+ "order by numascolti DESC "
				+ "Limit 20";
		try{
			List<Integer> nazioni= new LinkedList<Integer>();
			int id= -1;
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, mese);
			ResultSet res = st.executeQuery();
			while(res.next()){
			    id = res.getInt("countryid");	
				if(!nazioni.contains(id)){
					nazioni.add(id);
				}
			}
			conn.close();
			return nazioni;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}	
	}
	
	
	public Country getCountryById(int id){                               //dato un ID ottengo una country
		Connection conn = DBConnect.getConnection();
		String query = "select * from country c where c.id=?";
		Country c = null;
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id);
			ResultSet res = st.executeQuery();
			if(res.next()){
			    c = new Country (res.getInt("id"), res.getString("country"));
			}
			conn.close();
		}catch(SQLException e ){
			e.printStackTrace();
		}	
		return c;
	}
	
	
	public boolean  getNazioniAlmenoUnArtistaComune(int mese, int id1, int id2){
		Connection conn = DBConnect.getConnection();
		String query =" select l1.countryid,  l2.countryid "
				+ "from listening l1, listening l2, country c1, country c2 "
				+ "where l1.countryid=c1.id and l2.countryid=c2.id and l1.month=? and l2.month =? "
				+ "and l1.artistid=l2.artistid and l1.countryid<>l2.countryid and c1.id=? and c2.id=?";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, mese);
			st.setInt(1, id1);
			st.setInt(1, id2);
			ResultSet res = st.executeQuery();
			if(res.next()){
			    return true;
			} else {
				return false;
			}
		}catch(SQLException e ){
			e.printStackTrace();
			return false;
		}	
	}
	
	public int numeroArtisti(int mese, Country c1, Country c2){
		Connection conn = DBConnect.getConnection();
		String query =" select  count(l1.artistid) as num "
				+ "from listening l1, listening l2, country c1, country c2 "
				+ "where l1.month=? and l2.month=? and l1.countryid=c1.id and l2.countryid=c2.id "
				+ "and l1.artistid=l2.artistid  "
				+ "and l1.countryid=? and l2.countryid=? "
				+ "group by l1.countryid, l2.countryid";
		try{
			int conta =-1;
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, mese);
			st.setInt(1, c1.getId());
			st.setInt(1, c2.getId());
			ResultSet res = st.executeQuery();
			if(res.next()){
				conta = res.getInt("num");
			}
			return conta;
		}catch(SQLException e ){
			e.printStackTrace();
			return -1;
		}			
	}
	
	
	
	
//	public List<String> getArtistiPiuAscoltati(int mese){
//		Connection conn = DBConnect.getConnection();
//		List<String> artisti = new LinkedList<>();
//		String query = "select l.artistid, count(l.id) as numascolti from listening l  where l.month=12  "
//				+ "group by l.artistid order by numascolti DESC Limit 20";
//		try{
//			PreparedStatement st = conn.prepareStatement(query);
//			st.setInt(1, mese);
//			ResultSet res = st.executeQuery();
//			while(res.next()){
//				artisti.add("Artista: " +res.getInt("artistid")+ "  " +res.getInt("numascolti")+" \n ");				
//			}
//			conn.close();
//			return artisti;
//		}catch(SQLException e ){
//			e.printStackTrace();
//			return null;
//		}	
//	}
}
