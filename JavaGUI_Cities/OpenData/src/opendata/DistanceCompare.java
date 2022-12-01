package opendata;

import java.util.Comparator;

/**
 * The DistanceCompare class implements the {@link Comparator} interface and is used to compare two dates
 * @see PerceptronElderTraveller
 * @see PerceptronYoungTraveller
 * @author Ioannis Fovakis
 * @author Ioannis Komninos
 * @since 2.0
 * @version 1.0
 */
public class DistanceCompare implements Comparator<City>{

	@Override
	public int compare(City arg0, City arg1) {
		if(arg0.getDistance() < arg1.getDistance()) return -1;
		if(arg0.getDistance() > arg1.getDistance()) return 1;
		else return 0;
	}
	


}
