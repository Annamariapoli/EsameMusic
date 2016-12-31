package bean;

public class ArtistiEAscolti {
	
	private int id;
	private String nome;
	private int numAscolti;
	
	public ArtistiEAscolti(int id,String nome, int numAscolti) {
		super();
		this.id = id;
		this.nome=nome;
		this.numAscolti = numAscolti;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		ris =id+" "+nome+" "+numAscolti+" \n";
		return ris;
	}
	
	//forse compare numAscolti

}
