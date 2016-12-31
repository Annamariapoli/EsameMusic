package bean;


public class Country {
	private int id ;
	private String country ;
	
	public Country(int id, String country) {
		super();
		this.id = id;
		this.country = country;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArtist() {
		return country;
	}
	public void setArtist(String artist) {
		this.country = artist;
	}

	@Override
	public String toString() {
		return "Country[" + id + ", \"" + country + "\"]";
	}

	@Override
	public int hashCode() {      //genero io senno non riesco ad eliminare duplicati
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {       //mi basta conoscere id x vedere se sono = o meno
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (id != other.id)                  //id
			return false;
		return true;
	}
}
