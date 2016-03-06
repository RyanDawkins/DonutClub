package ryanddawkins.com.donutclub.data.access;

import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/6/16.
 */
public interface GetUserCallback {

    /**
     * This method will be called to send the user back
     * @param user
     */
    void onUserRetrieved(User user);

}
