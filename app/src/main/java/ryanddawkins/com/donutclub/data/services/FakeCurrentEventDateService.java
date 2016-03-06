package ryanddawkins.com.donutclub.data.services;

import java.util.Date;

/**
 * Created by ryan on 3/6/16.
 */
public class FakeCurrentEventDateService implements CurrentEventDateService {

    @Override
    public Date getCurrentEventDate() {

        Date date = new Date(2016, 1, 2);

        return date;
    }
}
