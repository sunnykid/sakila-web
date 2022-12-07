package vo;

public class Country {
	private int countryId;
	private String country;
	private String lastUpdate;
	public Country() {
		super();
		this.countryId = 0;
		this.country = null;
		this.lastUpdate = null;
	}

	public int getContryId() {
		return this.countryId;
	}
	
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getLastUpdate() {
		return this.lastUpdate;
	}
	
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


}
