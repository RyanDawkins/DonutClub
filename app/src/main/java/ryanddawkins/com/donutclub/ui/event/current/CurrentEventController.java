package ryanddawkins.com.donutclub.ui.event.current;

import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/3/16.
 */
public class CurrentEventController {

    private CurrentEventView currentEventView;

    public CurrentEventController(CurrentEventView currentEventView) {
        this.currentEventView = currentEventView;
    }

    public void handleRsvpClick() {
    }

    public void onUserSelected(User user) {

    }

}
