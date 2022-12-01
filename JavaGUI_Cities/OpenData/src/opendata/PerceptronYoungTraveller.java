/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opendata;

import java.util.ArrayList;
import java.util.Collections;

import Main.myFrame;

/**The PerceptronYoungTraveller class implements the {@link PerceptronTraveller} interface and is used to find the recommendations
* for the younger travelers. 
* @see PerceptronElderTraveller
* @see PerceptronMiddleTraveller
* @author Ioannis Fovakis
* @author Ioannis Komninos
* @since 1.0
* @version 2.0
*/
public class PerceptronYoungTraveller implements PerceptronTraveller{
    
   public  double weightsBias[]= new double[10];   // ta weights
   
   
   public void setWeightsBias(double[] weightsBias) {
	this.weightsBias = weightsBias;
   }

   
   @Override
   public ArrayList<City> recommend(){// i recommend arthrizei tis monades simfona me ta weights kai tin heaviside function.
	   								   //An i poli exei pano apo 5 monades pernaei stis recommended
	   
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
      
       return recommendedCities; //  epistrefete to arraylist me oles tis recommended poleis
   }
   
   @Override
   public ArrayList<City> recommend(boolean b){ // to idio me apo pano apla an i b einai true ta onomata ton poleon einai kefalaia eno an einai false einai mikra
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

   
   // Syanrthsh gia taksinomhsh mesw DistanceCompare
   @Override
   public ArrayList<City> sortReccomendations() {
		ArrayList<City> recommendedCities = recommend();
		DistanceCompare c = new DistanceCompare();
		Collections.sort(recommendedCities, c);
		return recommendedCities;
	}
   
}
