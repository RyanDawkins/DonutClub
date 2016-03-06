package ryanddawkins.com.donutclub.data.access;

import java.util.List;

import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/5/16.
 */
public interface RsvpListCallback {

    void onGetList(List<User> rsvpList);

}
