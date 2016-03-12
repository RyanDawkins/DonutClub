package ryanddawkins.com.donutclub.data.services;

import ryanddawkins.com.donutclub.data.access.GetUserCallback;

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

}
