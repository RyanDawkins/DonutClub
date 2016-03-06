package ryanddawkins.com.donutclub.data.pojo;

import java.util.Date;

/**
 * Created by ryan on 3/6/16.
 */
public class Rsvp {

    private Date date;
    private String location;
    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
