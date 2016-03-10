package ryanddawkins.com.donutclub.data.pojo;

/**
 * Created by ryan on 3/6/16.
 */
public class Rsvp {

    String date;
    String username;

    public Rsvp(){}

    public Rsvp(String date, String username) {
        this.date = date;
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
