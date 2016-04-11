package ryanddawkins.com.donutclub.data.access;

import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/13/16.
 */
public interface AuthAccess {

    void authenticate(String token, String type, AuthCallback authCallback);

    boolean isLoggedIn();

    User getCurrentUser();

    void setCurrentUser(User user);

    void saveCurrentUser(String userId);

}
