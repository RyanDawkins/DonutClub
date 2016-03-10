package ryanddawkins.com.donutclub.data.services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by ryan on 3/6/16.
 */
public class FakeCurrentEventDateService implements CurrentEventDateService {

    /**
     * Returns a statically set date object
     * @return
     */
    @Override
    public Date getCurrentEventDate() {

        Calendar calendar = new GregorianCalendar(2016, 0, 2);
        return calendar.getTime();
    }
}
