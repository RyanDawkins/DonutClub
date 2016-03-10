package ryanddawkins.com.donutclub.data.pojo;

import java.util.Date;

/**
 * Created by ryan on 3/3/16.
 */
public class Event {

    int eventId;
    Date date;
    String location;
    User host;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }
}