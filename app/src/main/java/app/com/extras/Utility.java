package app.com.extras;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/*
 * Created by Yash on 14/1/18.
 */


public class Utility
{
    private static String timePattern = "HH:mm";

    /* @method convertTimeStampToTime
     * @param  long value of timestamp
     * @return String format of time after converting timestamp time using SimpleDateFormat.*/
    public static String convertTimeStampToTime(long timestamp)
    {
        try
        {
            SimpleDateFormat df = new SimpleDateFormat(timePattern, Locale.US);
            return df.format(timestamp);
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return "";
    }

    /* @method differenceTimeStap
     * @param  long value of departure and arrival timestamp
     * @return String format of time after converting timestamp time and local IST format using SimpleDateFormat.*/

    public static String differenceTimeStap(long departure,long arrival)
    {
        long diffTime = arrival- departure;

        return covertTime(convertTimeStampToTime(diffTime));
    }

    // convert time to local IST format
    private static String covertTime(String time)
    {
        DateFormat sdf = new SimpleDateFormat(timePattern,Locale.US);
        Date date = null;
        try
        {
            date = sdf.parse(time);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        sdf.setTimeZone(TimeZone.getTimeZone("IST"));

        return sdf.format(date);
    }

    // get current date and return in String format
    public static String getCurrentDate()
    {
        Calendar c = Calendar.getInstance();

        String datePattern = "MMMM dd";

        SimpleDateFormat df = new SimpleDateFormat(datePattern);

        return df.format(c.getTime());
    }
}
