package ryanddawkins.com.donutclub.data.services;

import ryanddawkins.com.donutclub.data.access.GetUserCallback;
import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/6/16.
 */
public interface UserService {

    /**
     * This method asks for a single user and will send it back through a callback
     * @param getUserCallback
     * @param userId
     */
    void getUser(GetUserCallback getUserCallback, String userId);

    /**
     * Method to save the user with the userid given.
     * @param user
     * @param userId
     */
    void saveUser(User user, String userId);

}
