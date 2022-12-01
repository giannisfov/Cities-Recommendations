/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import opendata.*;

/** The Controller implements an application 
 * that recommends a City for three different travelers
 * 
 * @author Ioannis Fovakis
 * @author Ioannis Komninos
 * @version 2.0
 * 
 */
public class Controller {   
    /**
     * This method gets an already defined HashMap with string keys and string values and fills the keys
     * with the days of the week
     * @param daysOfTheWeek HashMap<String, String> value
     * @since 2.0
     */
	
    public static void fillMapKeys(HashMap<String, String> daysOfTheWeek) {
    	daysOfTheWeek.put("Monday", "");
    	daysOfTheWeek.put("Tuesday", "");
    	daysOfTheWeek.put("Wednesday", "");
    	daysOfTheWeek.put("Thursday", "");
    	daysOfTheWeek.put("Friday", "");
    	daysOfTheWeek.put("Saturday", "");
    	daysOfTheWeek.put("Sunday", "");
    }
    
    
    /**
     * This method gets an already defined HashMap with string keys and string values and fills the values with 
     * city names according to the day they were added on the ArrayList cities in the OpenData class
     * @param daysOfTheWeek HashMap<String, String> value
     * @since 2.0
     * @see OpenData
     */
    public static void fillMapValues(HashMap<String, String> daysOfTheWeek) {
    	SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
    	for (City c : myFrame.cities) {
    			String temp = daysOfTheWeek.get(simpleDateformat.format(c.getTimestamp()));
    			temp = temp.concat(c.getName() + " ");
    			daysOfTheWeek.replace(simpleDateformat.format(c.getTimestamp()), temp);
    	}
    }
    
    
    /**
     * This method gets a PerceptronTraveller object as a parameter, finds the top recommendations for the given traveler using the PerceptronTraveller.recommend()
     * and returns the number one recommendation for that traveler
     * @param traveller The only parameter of this method, an object of a class that implements the {@link PerceptronTraveller} interface
     * @return City The returned value is a City object which represents the top recommended city for the traveler given
     * @since 1.0
     */
    public static City cityRecommendation(PerceptronTraveller traveller) { 
    	ArrayList<City> Cities = traveller.recommend(); // girnaei to arraylist tis recommend me oles tis protinomenes poleis
    	double min = 1.1;
    	int pos = 0;
    	double vector[];
    	for(City city : Cities) {
    		vector = city.getVector();
    		if (vector[9] < min) {
    			min = vector[9]; //vriskei tin poli me tin mikroteri apostasi anamesa stin idia kai sto grafeio
    			pos = Cities.indexOf(city); // krataei to index tis gia na epistrafei sti main
    		}
    	}
    	
    	return Cities.get(pos);
    }
    
}
