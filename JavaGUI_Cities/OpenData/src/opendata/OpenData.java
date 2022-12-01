/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package opendata;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import weather.OpenWeatherMap;
import wikipedia.MediaWiki;

/**
 * This Class is responsible for getting all the Data from the APIs
 * 
 * @author Ioannis Fovakis
 * @author Ioannis Komninos
 * @since 1.0
 * @version 2.0
 *
 */

public class OpenData {
	
	/**
	 * The ArrayList that contains all the Cities.
	 */
	//public static ArrayList<City> cities; // to arraylist pou periexei oles tis poleis. Den einai topiko gia diefkolinsi prosvasis.
	/**
	 * double value of the Latitude of Athens
	 * Used for finding the distance between any city and Athens.
	 */
	private static double athensLat = 0;
	/**
	 * double value of the Longitude of Athens
	 * Used for finding the distance between any city and Athens.
	 */
	private static double athensLon = 0;
		
	public static double temp[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	
     // afti i sinartisi pernei ta dedomena apo ta API's kai gemizei to vector tis polis pou perniete ekeini ti stigmi
	/**
	 * This method is used for communicating with the APIs. However it does not put the data direclty in ArrayList, it stores the data
	 * in the City object that is given to it.
	 * The data is taken from two diffent APIs at the same time using another thread.
	 * @param city The first parameter of the method which is the name of the city.
	 * @param country The second parameter of the method which is the country of the city.
	 * @param appid The third parameter of the method which is the API key.
	 * @param c The forth parameter of the method which is the Object City that we want the data to get parsed in.
	 * @see City
	 * @throws IOException
	 * @since 1.0
	 */
	public static void RetrieveData(String city, String country, String appid, City c) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		Runnable thr = new myThread(c);
		Thread thread = new Thread(thr);
		thread.start();
		OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+","+country+"&APPID="+appid+""), OpenWeatherMap.class);
		
		double t=weather_obj.getMain().getTemp();
		temp[7]=(t-184)/147; // i thermokrasia Kelvin se min-max scaler
         
		double l=weather_obj.getClouds().getAll();
		temp[8]=l/100; // i sinefia / 100
         
		double k1=0, k2=0, k3=0, k4=0;
		if(city=="Athens" && country == "gr"){
			k4=0;
			athensLat=weather_obj.getCoord().getLat();
			athensLon=weather_obj.getCoord().getLon();
		}else{
			k1=weather_obj.getCoord().getLat();
			k2=weather_obj.getCoord().getLon();
			k3=distance(athensLat,athensLon,k1,k2,"K");
			k4=k3/15330;
		}
		temp[9]=k4;
		c.setDistance(k4);
		
		while(thread.isAlive()) {}
		c.setVector(temp);                
}

// Synarthsh h opoia pairnei ena Strin kai ena krithrio kai psaxnei mesa sto String poses fores uparxei to krhtirio auto
/**
 * This method takes an article (cityArticle) and a word (criterion) as its parameters and counts how many times the word shows up in the article.
 * @param cityArticle The first parameter of the method, a wikipedia article.
 * @param criterion The second parameter of the method, a word.
 * @return double value the count of the criterion inside the article.
 */
public static double countCriterionfCity(String cityArticle, String criterion) { // afti i sinartisi voithaei stin evresi lekseon kleidia sta arthra apo to wikipedia
	cityArticle=cityArticle.toLowerCase();
	int index = cityArticle.indexOf(criterion);
	double count = 0;
	while (index != -1) {
	    count++;
	    cityArticle = cityArticle.substring(index + 1);
	    index = cityArticle.indexOf(criterion);
	}
    if(count > 10){
    	return 1;
    }else{
    	return count/10.0;
    }

}

// Synarthh h opoia sygrinei dyo apostaseis
/**
 * This method takes the coordinates of two cities and returns the distance between them. The unit can be either Kilometers (if the fifth
 * parameter is 'K') or Nautical miles (if the fifth parameter is 'N').
 * @param lat1 The first parameter of the method which is the latitude of one city.
 * @param lon1 The second parameter of the method which is the longitude of one city.
 * @param lat2 The third parameter of the method which is latitude of the other city.
 * @param lon2 The forth parameter of the method which is longitude of the other city.
 * @param unit The fifth parameter of the method which is unit in which we want our data to be returned(K or N).
 * @return double value distance between the two cities in the unit that we want
 */
private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) { // afti i sinartisi einai ipefthini sto na vriskei tin apostasi tis polis apo to taksidiotiko grafio
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			if (unit.equals("K")) {
				dist = dist * 1.609344;
			} else if (unit.equals("N")) {
				dist = dist * 0.8684;
			}
			return dist;
		}
	}
}

/**
 * This Class is responsible for getting all the Data from the openweather api.
 * This Class implements the Runnable interface.
 * @author Ioannis Fovakis
 * @author Ioannis Komninos
 * @since 4.0
 * @version 1.0
 *
 */

class myThread implements Runnable {

	/**
	 * The city for which the program is trying to find the weather data.
	 */
	private City city;
	
	public myThread(City city) {
		this.city = city;
	}
	
	/**
	 * The run method of the Thread.
	 */
	@Override
	public void run() {
		try {
			wikiData(city.getName(), city.getCountry(), city);
		} catch (IOException e) {
			System.out.println("There was a problem with the API calls");
		}
	}
	
	/**
	 * This method is responsible for the communication between the program and the openweather API.
	 * @param city The name of the city, String value.
	 * @param country The country of the city, String value.
	 * @param c The City object.
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private void wikiData(String city, String country, City c) throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		MediaWiki mediaWiki_obj =  mapper.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles="+city+"&format=json&formatversion=2"),MediaWiki.class);
		String Article = mediaWiki_obj.getQuery().getPages().get(0).getExtract();
	         
		OpenData.temp[0] = OpenData.countCriterionfCity(Article, "coffe");
		OpenData.temp[1] = OpenData.countCriterionfCity(Article, "sea");
		OpenData.temp[2] = OpenData.countCriterionfCity(Article, "museam");
		OpenData.temp[3] = OpenData.countCriterionfCity(Article, "restaurant");
		OpenData.temp[4] = OpenData.countCriterionfCity(Article, "stadium");
		OpenData.temp[5] = OpenData.countCriterionfCity(Article, "bar");
		OpenData.temp[6] = OpenData.countCriterionfCity(Article, "club");
	}
}
