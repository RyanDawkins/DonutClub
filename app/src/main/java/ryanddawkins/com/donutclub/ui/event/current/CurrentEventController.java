package ryanddawkins.com.donutclub.ui.event.current;

import java.util.Date;
import java.util.List;

import ryanddawkins.com.donutclub.data.access.RsvpListAccess;
import ryanddawkins.com.donutclub.data.access.RsvpListCallback;
import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/3/16.
 */
public class CurrentEventController implements RsvpListCallback {

    private CurrentEventView currentEventView;

    private RsvpListAccess rsvpListAccess;

    public CurrentEventController(CurrentEventView currentEventView, RsvpListAccess rsvpListAccess) {
        this.currentEventView = currentEventView;
        this.rsvpListAccess = rsvpListAccess;
    }

    public void loadRsvpList() {

        // Build service to get this date object.
        Date date = new Date();

        this.rsvpListAccess.getRsvpList(this, date);
    }

    public void handleRsvpClick() {
    }

    public void onUserSelected(User user) {

    }

    @Override
    public void onGetList(List<User> rsvpList) {

    }
}
