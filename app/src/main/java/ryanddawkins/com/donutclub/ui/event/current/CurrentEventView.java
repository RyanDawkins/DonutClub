package ryanddawkins.com.donutclub.ui.event.current;

import java.util.List;

import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/3/16.
 */
public interface CurrentEventView {

    void setWhenDate(String whenDate);

    void setWhereLocation(String whereLocation);

    void setRsvpCount(String rsvpCount);

    void setDeadlineToRsvp(String deadlineToRsvp);

    void setRsvpList(List<User> rsvpList);

}
