package ryanddawkins.com.donutclub.data.services;

import ryanddawkins.com.donutclub.data.access.GetUserCallback;
import ryanddawkins.com.donutclub.data.access.UserAccess;

/**
 * Created by ryan on 3/6/16.
 */
public class UserAccessService implements UserService {

    private UserAccess userAccess;

    public UserAccessService(UserAccess userAccess) {
        this.userAccess = userAccess;
    }

    @Override
    public void getUser(GetUserCallback getUserCallback, String username) {
        this.userAccess.getUser(getUserCallback, username);
    }
}
