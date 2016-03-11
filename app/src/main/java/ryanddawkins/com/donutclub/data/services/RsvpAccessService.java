package ryanddawkins.com.donutclub.data.services;

import java.util.Date;

import ryanddawkins.com.donutclub.data.access.DataListenerAdapter;
import ryanddawkins.com.donutclub.data.access.GetUserCallback;
import ryanddawkins.com.donutclub.data.access.RsvpAccess;
import ryanddawkins.com.donutclub.data.formatters.EventDateFormatter;
import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/6/16.
 */
public class RsvpAccessService implements RsvpService {

    private RsvpAccess rsvpAccess;

    public RsvpAccessService(RsvpAccess rsvpAccess) {
        this.rsvpAccess = rsvpAccess;
    }

    public DataListenerAdapter getRsvpList(GetUserCallback getUserCallback, Date eventDate) {
        return this.rsvpAccess.getRsvpList(getUserCallback, eventDate);
    }

    public void rsvpUser(User user, String eventDateString) {
        this.rsvpAccess.rsvpUser(user.getUsername(), eventDateString);
    }

    public void rsvpUser(User user, Date date) {
        EventDateFormatter eventDateFormatter = new EventDateFormatter(date);
        this.rsvpUser(user, eventDateFormatter.formatMonthDayYear());
    }

}
