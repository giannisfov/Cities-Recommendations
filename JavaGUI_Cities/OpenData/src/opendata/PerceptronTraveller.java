/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package opendata;

import java.util.ArrayList;

/**
 * The PerceptronTraveller interface is used for the creation of the three concrete classes
 * {@link PerceptronElderTraveller}, {@link PerceptronMiddleTraveller} and {@link PerceptronYoungTraveller}.
 * @author Ioannis Fovakis
 * @author Ioannis Komninos
 * @since 1.0
 * @version 2.0
 */

// to interface pou xrisimopoioun oi 3 concrete classes Traveller
public interface PerceptronTraveller { // arxika einai public epidi xrisimopoiite sti Main.Controller.cityRecommendation(PerceptronTraveller traveller).
     
    /**
     * This method creates and returns an ArrayList with all the Cities that are recommended to the traveler.
     * @return ArrayList of City objects
     */
    public ArrayList<City> recommend();
    
    /**
     * This method acts exactly like recommend() but the user can chose between lower case and upper case for the names of the cities in the returned ArrayList
     * @param b The only parameter of the method, If b = true the names of the cities are in upper case, if b = false the names are in lower case 
     * @return ArrayList of City objects
     */
    public ArrayList<City> recommend(boolean b);
    
    /**
     * This method creates an ArrayList with all the Cities that are recommended to the traveler
     * using the recommend method and returns it sorted according to the age of the traveler.
     * If the object is of PerceptronYoungTraveller its sorted by the distance between the cities and Athens.
     * If the object is of PerceptronMiddleTraveller its sorted by the cities' timestamp.
     * If the object is of PerceptronElderTraveller its sorted by distance.
     * @return ArrayList of City objects.
     */
    public ArrayList<City> sortReccomendations();
}
