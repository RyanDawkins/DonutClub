package ryanddawkins.com.donutclub.data.access;

/**
 * Created by ryan on 3/4/16.
 */
public interface RsvpAccess {

    /**
     * This method is to get the list of rsvps for a given event and accepts a usercallback
     * to create a stream of users.
     * @param getUserCallback
     * @param eventId
     * @return an adapter so you can stop the listening for new users added to the rsvp list
     */
    DataListenerAdapter getRsvpList(GetUserCallback getUserCallback, String eventId);

    /**
     * This method will rsvp a user given a date string.
     * @param user
     * @param eventDateString
     */
    void rsvpUser(String user, String eventDateString);

}
