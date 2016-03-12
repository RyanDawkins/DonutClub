package ryanddawkins.com.donutclub.ui.event.current;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ryanddawkins.com.donutclub.data.access.EventCallback;
import ryanddawkins.com.donutclub.data.access.GetUserCallback;
import ryanddawkins.com.donutclub.data.pojo.Event;
import ryanddawkins.com.donutclub.data.pojo.User;
import ryanddawkins.com.donutclub.data.services.AuthService;
import ryanddawkins.com.donutclub.data.services.EventService;
import ryanddawkins.com.donutclub.data.services.RsvpService;

/**
 * Created by ryan on 3/3/16.
 */
public class CurrentEventPresenter implements GetUserCallback, EventCallback {

    private CurrentEventView currentEventView;
    private RsvpService rsvpService;
    private AuthService authService;
    private EventService eventService;
    private List<User> rsvpList;

    /**
     * Accepts the service that way that we can test just the controller without testing the service
     * in our unit tests.
     * @param currentEventView
     * @param rsvpService
     */
    public CurrentEventPresenter (CurrentEventView currentEventView, RsvpService rsvpService, AuthService authService, EventService eventService) {
        this.currentEventView = currentEventView;
        this.rsvpService = rsvpService;
        this.eventService = eventService;
        this.authService = authService;

        this.rsvpList = new ArrayList<User>();
    }

    public void initialize() {
        String groupId = this.currentEventView.getGroupFromIntent();
        this.eventService.getCurrentEvent(this, groupId);
    }

    /**
     * This method is to handle the RSVP click and to create a new rsvp for a date.
     */
    public void handleRsvpClick() {
    }

    /**
     * This method will get the user selected and pass it off to a new activity.
     * @param user
     */
    public void onUserSelected(User user) {
        this.currentEventView.navigateToProfile(user.getUsername());
    }

    /**
     * This method will pass a user into the list of users and begin to form the list of users
     * that will display in a list.
     * @param user
     */
    @Override
    public void onUserRetrieved(User user) {
        Log.d("user", user.getFirstName());
        this.rsvpList.add(user);
        this.currentEventView.setRsvpList(this.rsvpList);
    }

    @Override
    public void onRetrievedEvent(Event event) {
        this.currentEventView.setWhereLocation(event.getLocation());
        this.currentEventView.setWhenDate(event.getDate());
        this.currentEventView.setDeadlineToRsvp(event.getDeadline());

        this.rsvpService.getRsvpList(this, event.getEventId());
    }
}
