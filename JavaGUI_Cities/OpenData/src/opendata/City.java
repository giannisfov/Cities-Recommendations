/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opendata;

import java.util.Arrays;
import java.util.Date;

/**
 * The Class City is used to represent a city with all its features.
 *
 * @author Ioannis Fovakis
 * @author Ioannis Komninos
 * @since 1.0
 * @version 2.0
 */

public class City {
	/**
	 * double array vector contains the city's number of features.
	 * vector[0] -> cafe || 
	 * vector[1] -> sea || 
	 * vector[2] -> museums || 
	 * vector[3] -> restaurants || 
	 * vector[4] -> stadiums || 
	 * vector[5] -> bars || 
	 * vector[6] -> clubs || 
	 * vector[7] -> temperature in Kelvin || 
	 * vector[8] -> clouds || 
	 * vector[9] -> coordinates.
	 */
	private double vector[] = new double[10]; // to vector exei arxikopiithei prin mpoun oi nees times
	/**
	 * String value of the country in which the city is in.
	 */
	private String country;
	/**
	 * String value of the name of the city.
	 */
	private String name;
	/**
	 * Date value of when the city was added in the cities ArrayList in {@link OpenData}.
	 */
	private Date timestamp;
	/**
	 * double value of the distance between the city and Athens.
	 */
	private double distance;
	
	/**
	 * The constructor of the class City. It sets the name, the country and the timestamp according to the exact time it is called.
	 * @param name A String value of the name of the City.
	 * @param country A String value of the country in which the City is in.
	 */
	public City(String name, String country) { // sto constructor mpainoun onoma kai xora polis
		this.name = name;
		this.country = country;
		this.timestamp = new Date();
	}
	
	
	
	/**
	 * The getter of distance.
	 * @return double value distance.
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * The setter of distance.
	 * Changes the value of distance.
	 * @param double value distance.
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * Another constructor which is empty and is only used during the API calls.
	 */
	public City() {}
	
	/**
	 * The getter of timestamp.
	 * @return Date value timestamp.
	 */
	public Date getTimestamp() {
		return timestamp;
	}


	/**
	 * The setter of timestamp.
	 * Changes the value of timestamp.
	 * @param Date value timestamp.
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	
	/**
	 * The getter of name.
	 * @return String value name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * The setter of name.
	 * Changes the value of name.
	 * @param String value name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The getter of country.
	 * @return String value country.
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * The setter of country.
	 * Changes the value of country.
	 * @param String value country.
	 */
	public void setCountry(String country) { 
		this.country = country;
	}

	/**
	 * The setter of vector.
	 * Changes the value of vector.
	 * @param double[] vector.
	 */
	public void setVector(double vector[]) {
		this.vector = vector;
	}
	
	/**
	 * The getter of vector.
	 * @return double[] vector.
	 */
	public double[] getVector() {
		return this.vector;
	}
	
    @Override
    public String toString() { //  mia toString pou emfanizei ola ta stoixia tis polis
        return "City name: " + this.name + "\n"
        		+ "Country: " + this.country + "\n"
        		+ "City's Vector: " + Arrays.toString(this.vector) + '\n';
    }
    
    @Override
    public boolean equals(Object obj) {
         if (!(obj instanceof City)) {
            return false;
         }
         
         City tmp =  (City) obj;
         if(this.name.equals(tmp.getName()) && this.country.equals(tmp.getCountry())) {
        	 return true;
         }else {
        	 return false;
         }
         
    }
}
