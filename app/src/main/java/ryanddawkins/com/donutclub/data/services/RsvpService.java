package ryanddawkins.com.donutclub.data.services;

import java.util.Date;

import ryanddawkins.com.donutclub.data.access.DataListenerAdapter;
import ryanddawkins.com.donutclub.data.access.GetUserCallback;
import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/6/16.
 */
public interface RsvpService {

    /**
     * This method will make a call to get the list of users that are available and stream them
     * through the callback.
     * @param getUserCallback
     * @param eventId
     * @return
     */
    DataListenerAdapter getRsvpList(GetUserCallback getUserCallback, String eventId);

    /**
     * This method will rsvp the user to an event date string.
     * @param user
     * @param eventDateString
     */
    void rsvpUser(User user, String eventDateString);

    /**
     * This will format the date string into the proper format and send it to rsvpUser(User, String)
     * @param user
     * @param date
     */
    void rsvpUser(User user, Date date);

}
