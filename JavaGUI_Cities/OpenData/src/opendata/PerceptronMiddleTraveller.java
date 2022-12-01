/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opendata;

import java.util.ArrayList;
import java.util.Collections;

import Main.myFrame;

/**
 * The PerceptronMiddleTraveller class implements the {@link PerceptronTraveller} interface and is used to find the recommendations
 * for the middle aged travelers. 
 * @see PerceptronElderTraveller
 * @see PerceptronYoungTraveller
 * @author Ioannis Fovakis
 * @author Ioannis Komninos
 * @since 1.0
 * @version 2.0
 */
public class PerceptronMiddleTraveller implements PerceptronTraveller{
    
 /**
 * array of double values which contains all the weights for the class.
 */
	public  double weightsBias[]= new double[10];
    
	public void setWeightsBias(double[] weightsBias) {
		this.weightsBias = weightsBias;
	   }

	@Override
	public ArrayList<City> recommend(){ // koitakste sxolia sto class PerceptronYoungTraveller
	   ArrayList<City> recommendedCities = new ArrayList<City>();
       for(City city : myFrame.cities) {
    	   double sum = 0;
    	   double vector[] = city.getVector();
    	   for(int i = 0; i < vector.length; i++) {
    		   if (vector[i] > this.weightsBias[i]) {
    			   sum += 1;
    		   }
    	   }
    	   if (sum >= 5 && !(city.getName()=="Athens")) {
    		   recommendedCities.add(city);
    	   }
       }
       
       return recommendedCities;
	}
	
	@Override
	public ArrayList<City> recommend(boolean b){
	   ArrayList<City> recommendedCities = recommend();
	   if (b) {
		   for(City city : recommendedCities) {
			   city.setName(city.getName().toUpperCase());
		   }
	   } else {
		   for(City city : recommendedCities) {
			   city.setName(city.getName().toLowerCase());
		   }
	   }
	   
	   return recommendedCities;
	}

   // Syanrthsh gia taksinomhsh mesw DateCompare
   @Override
   public ArrayList<City> sortReccomendations() {
	  ArrayList<City> recommendedCities = recommend();
	  DateCompare dateCompare = new DateCompare();
	  Collections.sort(recommendedCities, dateCompare);
	  return recommendedCities;
   }

    
}
