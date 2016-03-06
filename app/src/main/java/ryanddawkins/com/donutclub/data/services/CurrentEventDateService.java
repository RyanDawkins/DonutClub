package ryanddawkins.com.donutclub.data.services;

import java.util.Date;

/**
 * Created by ryan on 3/6/16.
 */
public interface CurrentEventDateService {

    /**
     * Get the current events date for looking up an event
     * @return
     */
    Date getCurrentEventDate();

}
