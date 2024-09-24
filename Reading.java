
public class Reading {
	private String region;
	private String country;
	private String state;
	private String city;
	private short month;
	private short day;
	private short year;
	private double avgTemp;
	private Reading next = null;
	
	
	/**Constructor with all parameters
	 * @param region
	 * @param country
	 * @param state
	 * @param city
	 * @param month
	 * @param day
	 * @param year
	 * @param avgTemp
	 * @param next
	 */
	public Reading(String region, String country, String state, String city, short month, short day, short year,
			double avgTemp) {
		setRegion(region);
		setCountry(country);
		setState(state);
		setCity(city);
		setMonth(month);
		setDay(day);
		setYear(year);
		setAvgTemp(avgTemp);

	}
	
	//Getters and Setters
	
	public void setNext(Reading n)
	{
		next = n;
	}
	
	public Reading getNext()
	{
		return next;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * @return the month
	 */
	public short getMonth() {
		return month;
	}
	
	/**
	 * @param month the month to set
	 */
	public void setMonth(short month) {
		this.month = month;
	}
	
	/**
	 * @return the day
	 */
	public short getDay() {
		return day;
	}
	
	/**
	 * @param day the day to set
	 */
	public void setDay(short day) {
		this.day = day;
	}
	
	/**
	 * @return the year
	 */
	public short getYear() {
		return year;
	}
	
	/**
	 * @param year the year to set
	 */
	public void setYear(short year) {
		this.year = year;
	}
	
	/**
	 * @return the avgTemp
	 */
	public double getAvgTemp() {
		return avgTemp;
	}
	
	/**
	 * @param avgTemp the avgTemp to set
	 */
	public void setAvgTemp(double avgTemp) {
		this.avgTemp = avgTemp;
	}
}
