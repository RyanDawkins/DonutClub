package ryanddawkins.com.donutclub.ui.event.current;

import java.util.List;

import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/3/16.
 */
public interface CurrentEventView {

    /**
     * Set the when date in the view
     * @param whenDate
     */
    void setWhenDate(String whenDate);

    /**
     * Sets the location for where in the view
     * @param whereLocation
     */
    void setWhereLocation(String whereLocation);

    /**
     * Sets the rsvp count in the view
     * @param rsvpCount
     */
    void setRsvpCount(String rsvpCount);

    /**
     * Sets the deadline to rsvp in the view
     * @param deadlineToRsvp
     */
    void setDeadlineToRsvp(String deadlineToRsvp);

    /**
     * Sets the list of rsvps in the view
     * @param rsvpList
     */
    void setRsvpList(List<User> rsvpList);

    /**
     * This method navigates you to a profile passing the users username.
     * @param username
     */
    void navigateToProfile(String username);
}
