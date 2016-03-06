package ryanddawkins.com.donutclub.data.access;

/**
 * Created by ryan on 3/6/16.
 */
public interface UserAccess {

    /**
     * This allows you to get a single user and have a callback to store it after.
     * @param getUserCallback
     * @param username
     */
    void getUser(GetUserCallback getUserCallback, String username);

}
