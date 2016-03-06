package ryanddawkins.com.donutclub.data.pojo;

import java.util.Date;

/**
 * Created by ryan on 3/6/16.
 */
public class Rsvp {

    private Date date;
    private String username;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
