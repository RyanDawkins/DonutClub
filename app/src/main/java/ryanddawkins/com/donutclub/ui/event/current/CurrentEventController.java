package ryanddawkins.com.donutclub.ui.event.current;

import java.util.Date;

import ryanddawkins.com.donutclub.data.access.GetUserCallback;
import ryanddawkins.com.donutclub.data.pojo.User;
import ryanddawkins.com.donutclub.data.services.CurrentEventDateService;
import ryanddawkins.com.donutclub.data.services.FakeCurrentEventDateService;
import ryanddawkins.com.donutclub.data.services.IRsvpService;

/**
 * Created by ryan on 3/3/16.
 */
public class CurrentEventController implements GetUserCallback {

    private CurrentEventView currentEventView;
    private IRsvpService rsvpService;
    private CurrentEventDateService currentEventDateService;

    /**
     * Accepts the service that way that we can test just the controller without testing the service
     * in our unit tests.
     * @param currentEventView
     * @param rsvpService
     */
    public CurrentEventController(CurrentEventView currentEventView, IRsvpService rsvpService) {
        this.currentEventView = currentEventView;
        this.rsvpService = rsvpService;
        this.currentEventDateService = new FakeCurrentEventDateService();
    }

    /**
     * This method will lookup the current event date and send a callback back to the controller
     */
    public void loadRsvpList() {

        Date date = this.currentEventDateService.getCurrentEventDate();

        this.rsvpService.getRsvpList(this, date);
    }

    /**
     * This method is to handle the RSVP click and to create a new rsvp for a date.
     */
    public void handleRsvpClick() {
        Date date = this.currentEventDateService.getCurrentEventDate();
        this.rsvpService.rsvpUser(null, date);
    }

    /**
     * This method will get the user selected and pass it off to a new activity.
     * @param user
     */
    public void onUserSelected(User user) {

    }

    /**
     * This method will pass a user into the list of users and begin to form the list of users
     * that will display in a list.
     * @param user
     */
    @Override
    public void onUserRetrieved(User user) {

    }
}
