package ryanddawkins.com.donutclub.ui.event.current;

import java.util.Date;

import ryanddawkins.com.donutclub.data.access.GetUserCallback;
import ryanddawkins.com.donutclub.data.access.RsvpListAccess;
import ryanddawkins.com.donutclub.data.pojo.User;
import ryanddawkins.com.donutclub.data.services.CurrentEventDateService;
import ryanddawkins.com.donutclub.data.services.FakeCurrentEventDateService;

/**
 * Created by ryan on 3/3/16.
 */
public class CurrentEventController implements GetUserCallback {

    private CurrentEventView currentEventView;

    private RsvpListAccess rsvpListAccess;

    public CurrentEventController(CurrentEventView currentEventView, RsvpListAccess rsvpListAccess) {
        this.currentEventView = currentEventView;
        this.rsvpListAccess = rsvpListAccess;
    }

    public void loadRsvpList() {

        // Build service to get this date object.
        CurrentEventDateService currentEventDateService = new FakeCurrentEventDateService();

        Date date = currentEventDateService.getCurrentEventDate();

        this.rsvpListAccess.getRsvpList(this, date);
    }

    public void handleRsvpClick() {
    }

    public void onUserSelected(User user) {

    }

    @Override
    public void onUserRetrieved(User user) {

    }
}
