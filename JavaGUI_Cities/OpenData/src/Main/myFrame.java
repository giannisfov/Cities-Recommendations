package Main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import opendata.City;
import opendata.OpenData;
import opendata.PerceptronElderTraveller;
import opendata.PerceptronMiddleTraveller;
import opendata.PerceptronYoungTraveller;


/**
 * This Class has the main method and is responsible for all the gui operations.
 * This Class includes all the gui assets and other important data and methods.
 * 
 * @author Ioannis Fovakis
 * @author Ioannis Komninos
 * @since 3.0
 * @version 2.0
 *
 */
public class myFrame implements ActionListener{
	
	//1st Frame
	JFrame frame;
	JLabel check;
	JRadioButton rb1;
	JRadioButton rb2;
	JRadioButton rb3;	
	
	//2st Frame
	JFrame frame2;
	JPanel panel4;
	JButton next;
	JComboBox<Double> s1;
	JComboBox<Double> s2;
	JComboBox<Double> s3;
	JComboBox<Double> s4;
	JComboBox<Double> s5;
	JComboBox<Double> s6;
	JComboBox<Double> s7;
	
	//3stFrame
	JFrame city;
	JButton button;	
	static JLabel lb;
	JTextField text;
	JTextField text2;	
	JPanel panel2;
	JButton enough;
	JButton clear;
	JLabel rec;	
	
	//4st Frame
	JPanel panel3;
	JButton showRec;
	JButton showTop;
	JButton sortRec;
	JFrame frame3;
	static JLabel lb3;
	static JButton personalized;
	
	public static double temp2[]= new double[10];	
	HashMap<String, String> daysOfTheWeek;	
	public static String traveller;
	public static ArrayList<City> cities;
	ObjectMapper mapper = new ObjectMapper();
	
	ArrayList<String> recommended;
	
	java.util.logging.Logger logger= java.util.logging.Logger.getLogger(myFrame.class.getName());

	public static void main(String [] args) throws IOException {		
		 new myFrame();
	 }
	
	
	
	/**
	 * The constructor of the myFrame object.
	 * @throws IOException
	 * @version 2.0
	 *
	 */
	@SuppressWarnings("deprecation")
	public myFrame() throws IOException {
		
		
		// Log file
		 FileHandler fh;   
		 try {  

		        // This block configure the logger with handler and formatter  
		        fh = new FileHandler("MyLogFile.log");  
		        logger.addHandler(fh);
		        SimpleFormatter formatter = new SimpleFormatter();  
		        fh.setFormatter(formatter);  

		    } catch (SecurityException e) {  
		        e.printStackTrace();  
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    }  
		 
		// HashMap
		daysOfTheWeek = new HashMap<String, String>();			
		
		// Json file
		TypeFactory type = TypeFactory.defaultInstance();
		if (new File("cities.json").exists()) {
			cities = mapper.readValue(new File("cities.json"), type.constructCollectionType(ArrayList.class, City.class));
		} else {
			cities = new ArrayList<>();
		}
		
		checkCity(new City("Athens", "gr"));
	    Controller.fillMapKeys(daysOfTheWeek);	
		
	   
	    
		// 1st Frame 
		frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 5, 30));
		JLabel label= new JLabel("Age:");
		check = new JLabel("");
		ButtonGroup options= new ButtonGroup();
		rb1= new JRadioButton("16-25");
		rb2= new JRadioButton("25-60");
		rb3= new JRadioButton("60-115");
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		rb3.addActionListener(this);			
		options.add(rb1);
		options.add(rb2);
		options.add(rb3);	
		panel.add(label);
		panel.add(rb1);
		panel.add(rb2);
		panel.add(rb3);
		panel.add(check);	
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Traveller");
		frame.setSize(500,100);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.show();
		
		
		//2st Frame
		  Double[] numbers= {0.0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0};
		  frame2= new JFrame("Choose your criteria");
		  panel4= new JPanel();
		  next= new JButton("Next");
		  next.addActionListener(this);	  
		  JLabel cafe= new JLabel("Cafe");
		  s1= new JComboBox<Double>(numbers);	
		  cafe.setPreferredSize(new Dimension(100,80));
		  s1.setPreferredSize(new Dimension(80,50));
		  s1.addActionListener(this);		  
		  JLabel sea= new JLabel("Sea");
		  s2= new JComboBox<Double>(numbers);	
		  sea.setPreferredSize(new Dimension(100,80));
		  s2.setPreferredSize(new Dimension(80,50));
		  s2.addActionListener(this);		  
		  JLabel museum= new JLabel("Museum");
		  s3= new JComboBox<Double>(numbers);	
		  museum.setPreferredSize(new Dimension(100,80));
		  s3.setPreferredSize(new Dimension(80,50));
		  s3.addActionListener(this);		  
		  JLabel restaurant= new JLabel("Restaurant");
		  s4= new JComboBox<Double>(numbers);	
		  restaurant.setPreferredSize(new Dimension(100,80));
		  s4.setPreferredSize(new Dimension(80,50));
		  s4.addActionListener(this);		  
		  JLabel stadium= new JLabel("Stadium");
		   s5= new JComboBox<Double>(numbers);	
		  stadium.setPreferredSize(new Dimension(100,80));
		  s5.setPreferredSize(new Dimension(80,50));
		  s5.addActionListener(this);		  
		  JLabel bar= new JLabel("Bar");
		  s6= new JComboBox<Double>(numbers);	
		  bar.setPreferredSize(new Dimension(100,80));
		  s6.setPreferredSize(new Dimension(80,50));
		  s6.addActionListener(this);	  
		  JLabel club= new JLabel("Club");
		  s7= new JComboBox<Double>(numbers);	
		  club.setPreferredSize(new Dimension(100,80));
		  s7.setPreferredSize(new Dimension(80,50));
		  s7.addActionListener(this);		  	  
		  panel4.add(cafe);
		  panel4.add(s1);
		  panel4.add(sea);
		  panel4.add(s2);
		  panel4.add(museum);
		  panel4.add(s3);
		  panel4.add(restaurant);
		  panel4.add(s4);
		  panel4.add(stadium);
		  panel4.add(s5);
		  panel4.add(bar);
		  panel4.add(s6);
		  panel4.add(club);
		  panel4.add(s7);
		  panel4.add(next);		  
		  frame2.setContentPane(panel4);
		  frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  frame2.setLocation(1000,200);
		  frame2.setSize(250,670);
		
		  
		
		//3st Frame
		city = new JFrame();
		panel2 = new JPanel();
		text = new JTextField();
		text.setPreferredSize(new Dimension(100,30));
		text2= new JTextField();
		text2.setPreferredSize(new Dimension(100,30));
		enough= new JButton("Next");
		enough.setEnabled(cities.size() >= 10);
		enough.addActionListener(this);
		rec= new JLabel("");		
		if (cities.size() > 0) {
			 rec.setText("You have already added " + cities.size() +" cities");
		 }
		JLabel add = new JLabel("City:");
		JLabel add2 = new JLabel("Country:");
		lb= new JLabel();
		button = new JButton("Add");
		button.addActionListener(this);
		clear = new JButton("Clear");
		clear.addActionListener(this);
		panel2.add(add);
		panel2.add(text);
		panel2.add(add2);
		panel2.add(text2);
		panel2.add(button);
		panel2.add(lb);
		panel2.add(rec);
		panel2.add(enough);
		panel2.add(clear);
		
		city.setContentPane(panel2);
		city.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		city.setSize(470,120);
		city.setLocationRelativeTo(null); 
		
		
		//4st Frame
		frame3= new JFrame("Traveller");
		panel3 = new JPanel();
		showRec= new JButton("Show recommendations");
		showRec.addActionListener(this);
		showTop= new JButton("Show top recommendation");
		showTop.addActionListener(this);
		sortRec= new JButton("Sort recommendations");
		sortRec.addActionListener(this);
		personalized= new JButton("Personalized recommendation");
	    lb3 = new JLabel();		
		panel3.add(showRec);
		panel3.add(showTop);
		panel3.add(sortRec);
		panel3.add(personalized);
		panel3.add(lb3);
		frame3.setContentPane(panel3);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.setTitle("Traveller");
		frame3.setSize(800,110);
		frame3.setLocationRelativeTo(null);
		
	  
	}


	/**
	 *This method is responsible for all the gui operations. Everytime a gui element is used it reacts accordingly.
	 */
	@SuppressWarnings("deprecation")
	@Override
	 public void actionPerformed(ActionEvent e) {
		
		int f;
		 
		 // Select age
		 if(e.getSource()==rb1 || e.getSource()==rb2 || e.getSource()==rb3) {
			frame2.show();
			frame.setVisible(false);
			if(e.getSource()==rb1) {
				traveller="young";
				
			}else if(e.getSource()==rb2) {
				traveller="middle";
			}else {
				traveller="elder";
			}
			
			logger.info("Age:"+traveller);
		 }
		
		 
		
		 // Next
		 if(e.getSource()==next) {
			 city.show();
			 frame2.setVisible(false);
			 
			 //Set weightBias
			 if(traveller=="young") {
				 PerceptronYoungTraveller young = new PerceptronYoungTraveller();		 
				 temp2[0]=(double) s1.getSelectedItem();
				 temp2[1]=(double) s2.getSelectedItem();
				 temp2[2]=(double) s3.getSelectedItem();
				 temp2[3]=(double) s4.getSelectedItem();
				 temp2[4]=(double) s5.getSelectedItem();
				 temp2[5]=(double) s6.getSelectedItem();
				 temp2[6]=(double) s7.getSelectedItem();		 
				 temp2[7]=0.1;
				 temp2[8]=-0.2;
				 temp2[9]=0.2;
				 young.setWeightsBias(temp2);
			 }else if(traveller=="middle") {
				 PerceptronMiddleTraveller middle = new PerceptronMiddleTraveller();		 
				 temp2[0]=(double) s1.getSelectedItem();
				 temp2[1]=(double) s2.getSelectedItem();
				 temp2[2]=(double) s3.getSelectedItem();
				 temp2[3]=(double) s4.getSelectedItem();
				 temp2[4]=(double) s5.getSelectedItem();
				 temp2[5]=(double) s6.getSelectedItem();
				 temp2[6]=(double) s7.getSelectedItem();
				 temp2[7]=0.3;
				 temp2[8]=0;
				 temp2[9]=0.1;
				 middle.setWeightsBias(temp2);
			 }else {
				 PerceptronElderTraveller elder = new PerceptronElderTraveller();		 
				 temp2[0]=(double) s1.getSelectedItem();
				 temp2[1]=(double) s2.getSelectedItem();
				 temp2[2]=(double) s3.getSelectedItem();
				 temp2[3]=(double) s4.getSelectedItem();
				 temp2[4]=(double) s5.getSelectedItem();
				 temp2[5]=(double) s6.getSelectedItem();
				 temp2[6]=(double) s7.getSelectedItem();
				 temp2[7]=0.3;
				 temp2[8]=0;
				 temp2[9]=0.1;
				 elder.setWeightsBias(temp2);
			 }
			
			 logger.info("Logger critiria: "+temp2[0]+","+temp2[1]+","+temp2[2]+","+temp2[3]+","+temp2[4]+","+temp2[5]+","+temp2[6]);
		 }
		 
		 
		 // Add City and Country
		 if(e.getSource()==button) {
			 String city= text.getText();
			 String country=text2.getText();
			 try {
				 f = checkCity(new City(city,country));
			 } catch (IOException ioe) {
				 lb.setText("There was a problem with the API calls");
				 f = 2;
				 logger.log(null, "Exception occur with API calls", ioe);
			 }
			 if(f == 1) {
				 lb.setText("City not found");
			 }else if(f==0) {
				 lb.setText("City added:"+ text.getText());
				 rec.setText("You have added " + cities.size() +" cities");
				 logger.info("Logger added city:"+city);
				 try {
					mapper.writeValue(new File("cities.json"), cities);
					logger.info("City added to json file");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					logger.log(null, "Exception occur", e1);
				}
			 }
			 if(cities.size() >= 10) {
				 enough.setEnabled(true);
			 }
			
		 }
		 
		//Clear
		 if(e.getSource() == clear) {
			 cities.clear();
			 rec.setText("All cities have been removed");
			 enough.setEnabled(false);
			 logger.info("All cities were removed from the json file");
			 try {
					mapper.writeValue(new File("cities.json"), cities);
			 } catch (IOException e1) {
					lb.setText("there was a problem with the cities.json file");
					logger.log(null, "Exception occur", e1);
			 }
		 }
		 
		 // Next
		 if(e.getSource()==enough) {
			 Controller.fillMapValues(daysOfTheWeek);
			 try {
				mapper.writeValue(new File("cities.json"), cities);
			} catch (IOException e1) {
				lb.setText("there was a problem with the cities.json file");
				logger.log(null, "Exception occu with json file", e1);
			}
			 city.setVisible(false);
			 frame3.setVisible(true);
			 frame3.show();
		 }
		 
		 
		 // Show recommendations
		 if(e.getSource()==showRec) {
			 if(traveller=="young") {
				 PerceptronYoungTraveller young = new PerceptronYoungTraveller();
				 ArrayList<City> Cities = young.recommend();
				 recommended = new ArrayList<String>();
				 for(City c: Cities) {
					 recommended.add(c.getName());
					 recommended.add(" ");
				 }                
				 String csv = String.join("\t", recommended);
				 lb3.setText("Recommendations: " +csv);
				
			 }else if(traveller=="middle") {
				 PerceptronMiddleTraveller middle = new PerceptronMiddleTraveller();
			    ArrayList<City> Cities = middle.recommend();
			    recommended = new ArrayList<String>();
			    for(City c: Cities) {
					 recommended.add(c.getName());
					 recommended.add(" ");
				 }
			     String csv = String.join("\t", recommended);
			     lb3.setText("Recommendations: " +csv);
				 
			 }else {
				 PerceptronElderTraveller elder = new PerceptronElderTraveller();
				 ArrayList<City> Cities = elder.recommend();
				 recommended = new ArrayList<String>();
				 for(City c: Cities) {
					 recommended.add(c.getName());
					 recommended.add(" ");
				 }
				 String csv = String.join("\t", recommended);
				 lb3.setText("Recommendations: " +csv); 	
				 
			 } 
		 }
		 
		 
		 // Show top recommendation
		 if(e.getSource()==showTop) {
			 if(traveller=="young") {
				 PerceptronYoungTraveller young = new PerceptronYoungTraveller();
				  City reccommendation= Controller.cityRecommendation(young);
				  lb3.setText("Top recommendation: "+ reccommendation.getName());
			 }else if(traveller=="middle") {
				 PerceptronMiddleTraveller middle = new PerceptronMiddleTraveller();
				 City reccommendation= Controller.cityRecommendation(middle);
				  lb3.setText("Top recommendation: "+ reccommendation.getName());		   
			 }else {
				 PerceptronElderTraveller elder = new PerceptronElderTraveller();
				 City reccommendation= Controller.cityRecommendation(elder);
				 lb3.setText("Top recommendation: "+ reccommendation.getName());			 
		    }
		 }
		 
		 
		 
		 // Sort recommendations		 
		 if(e.getSource()==sortRec) {
			 if(traveller=="young") {
				 PerceptronYoungTraveller young = new PerceptronYoungTraveller();
				 ArrayList<City> Rec= young.sortReccomendations();
				 ArrayList<String> sortRec= new ArrayList<>();
				 for(City c: Rec) {
					 sortRec.add(c.getName());
					 sortRec.add(" ");
				 }
				 String csv = String.join("\t", sortRec);
				 lb3.setText("Sorted: " +csv);
			 }else if(traveller=="middle") {
				 PerceptronMiddleTraveller middle = new PerceptronMiddleTraveller();
				ArrayList<City> Rec= middle.sortReccomendations();
				 ArrayList<String> sortRec= new ArrayList<>();
				 for(City c: Rec) {
					 sortRec.add(c.getName());
					 sortRec.add(" ");
				 }
				 String csv = String.join("\t", sortRec);
				 lb3.setText("Sorted: " +csv); 		   
			 }else {
				 PerceptronElderTraveller elder = new PerceptronElderTraveller();
					ArrayList<City> Rec= elder.sortReccomendations();
					 ArrayList<String> sortRec= new ArrayList<>();
					 for(City c: Rec) {
						 sortRec.add(c.getName());
						 sortRec.add(" ");
					 }
					 String csv = String.join("\t", sortRec);
					 lb3.setText("Sorted: " +csv);			 
		    }
		 }
		
		 personalizedRecommend();	 
		 
}
	 
	 
	// i sinartisi tsekarei an iparxei i city i idi sto arraylist kai an iparxei
/**
* This method is used to check if a city already exists in the cities ArrayList. If it does it prints its timestamp and if not it adds
* it in the ArrayList.
* @param city The only parameter of the method, the city that we want to be checked.
* @throws IOException
* @return int The returned value is an int. 0 if the city gets added succesfully to the arraylist, 
* 1 if there is a problem with the json and 2 if the city has already been added to the arraylist.
*/
 private static int checkCity(City city) throws IOException {
	String appid ="04611ab85bbb3285a5e1677b5d3ee7c4";
	for(City c : cities) {
		if (c.equals(city)) {
			lb.setText(c.getName() + " was added at " + c.getTimestamp());
			return 2;
		}
	}
	
	try {
		OpenData.RetrieveData(city.getName(), city.getCountry(), appid, city); // an den iparxei vriskei oles tis plirofories apo ta api
		if(!(city.getName().equals("Athens") && city.getCountry().equals("gr"))) {
			cities.add(city); // kai ti vazei sto arraylist
		}
		return 0;
	} catch(IOException e) {
		return 1;
	}
  
 }


 
 /**
 * This method generates the pernonalised recommendation using a dot product.
 * @version 1.0
 */
private static void personalizedRecommend() {	  
	double[] dotproduct= new double[cities.size()];
	for(int i = 0; i < cities.size(); i++) {
	 for(int j=0;j<7;j++) {
		 dotproduct[i] += cities.get(i).getVector()[j] * temp2[j];
	 }
	}	 
	double max=0;
	int m = 0;
	for(int k=0;k < cities.size();k++) {
		if(dotproduct[k] > max) {
			max=dotproduct[k];
			m=k;
		}
	}
	int l=m;
	 personalized.addActionListener(
			 (e) -> lb3.setText("Personalized Recommendation: "+ cities.get(l).getName() )
	 );
	
 }

 
}
	 
	 
	 
	 
	 
