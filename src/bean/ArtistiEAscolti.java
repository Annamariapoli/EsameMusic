package bean;

public class ArtistiEAscolti {
	
	private int artistid;
	private String nome;
	private int numAscolti;
	
	public ArtistiEAscolti(int artistid,String nome, int numAscolti) {
		super();
		this.artistid = artistid;
		this.nome=nome;
		this.numAscolti = numAscolti;
	}
	public int getId() {
		return artistid;
	}
	public void setId(int id) {
		this.artistid = id;
	}
	public int getNumAscolti() {
		return numAscolti;
	}
	public void setNumAscolti(int numAscolti) {
		this.numAscolti = numAscolti;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString(){
		String ris ;
		ris =artistid+" "+nome+" "+numAscolti+" \n";
		return ris;
	}
	
	//forse compare numAscolti

}
