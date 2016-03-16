package ryanddawkins.com.donutclub.data.access;

import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/6/16.
 */
public interface UserAccess {

    /**
     * This allows you to get a single user and have a callback to store it after.
     * @param getUserCallback
     * @param userId
     */
    void getUser(GetUserCallback getUserCallback, String userId);

    void saveUser(User user,String userId);

}
