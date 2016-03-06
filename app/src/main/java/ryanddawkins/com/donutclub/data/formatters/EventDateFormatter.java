package ryanddawkins.com.donutclub.data.formatters;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ryan on 3/5/16.
 */
public class EventDateFormatter {

    private Date date;

    /**
     * Takes the data object for future processing
     * @param date
     */
    public EventDateFormatter(Date date) {
        this.date = date;
    }

    /**
     * This method formats the date into a MM-dd-YYYY format. Example: 01-02-2016
     * @return
     */
    public String formatMonthDayYear() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        return simpleDateFormat.format(this.date);
    }

}
