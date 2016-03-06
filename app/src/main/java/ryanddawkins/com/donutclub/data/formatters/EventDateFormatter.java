package ryanddawkins.com.donutclub.data.formatters;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ryan on 3/5/16.
 */
public class EventDateFormatter {

    private Date date;

    public EventDateFormatter(Date date) {
        this.date = date;
    }

    public String formatMonthDayYear() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        return simpleDateFormat.format(this.date);
    }

}
