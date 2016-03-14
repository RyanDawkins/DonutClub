package ryanddawkins.com.donutclub.data.services;

import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/6/16.
 */
public interface AuthService {

    /**
     * Method to check whether or not the user is signed in.
     * @return
     */
    boolean isSignedin();

    /**
     * This method allows you to get the current user that is signed in.
     * @return
     */
    User getCurrentUser();

    /**
     * Method to store the current user in memory.
     * @param user
     */
    void setCurrentUser(User user);

}
