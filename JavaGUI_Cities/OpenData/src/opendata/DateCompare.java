package opendata;

import java.util.Comparator;

/**
 * The DateCompare class implements the {@link Comparator} interface and is used to compare two dates
 * @see PerceptronMiddleTraveller
 * @author Ioannis Fovakis
 * @author Ioannis Komninos
 * @since 2.0
 * @version 1.0
 *
 */
public class DateCompare implements Comparator<City> {

	@Override
	public int compare(City arg0, City arg1) {
		if (arg0.getTimestamp().before(arg1.getTimestamp()) )  {
            return -1;
        } else if (arg0.getTimestamp().after(arg1.getTimestamp()) ) {
            return 1;
        } else {
            return 0;
        }        
	}
	

}
