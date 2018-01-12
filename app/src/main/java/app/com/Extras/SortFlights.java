package app.com.Extras;

import java.util.ArrayList;
import java.util.Collections;
import app.com.model.Flights;
import app.com.model.SortedFlight;

/*
 * Created by Yash on 13/1/18.
 */

public class SortFlights
{
    public static ArrayList<SortedFlight> getSortedFlight(ArrayList<Flights> flights, ArrayList<SortedFlight> sortedFlights)
    {
        for(int i = 0; i < flights.size(); i++)
        {
            Collections.sort(flights.get(i).getFareArrayList(), new FareComparator());

            SortedFlight sortedFlight = new SortedFlight();

            sortedFlight.setIndexPosition(i);
            sortedFlight.setDepartureTime(flights.get(i).getDepartureTime());
            sortedFlight.setArrivalTime(flights.get(i).getArrivalTime());
            sortedFlight.setFare(flights.get(i).getFareArrayList().get(0).getFare());
            sortedFlight.setAirlineCode(flights.get(i).getAirlineCode());

            sortedFlights.add(sortedFlight);
        }

        return sortedFlights;
    }
}
