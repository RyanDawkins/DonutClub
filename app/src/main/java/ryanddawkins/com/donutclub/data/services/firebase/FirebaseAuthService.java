package ryanddawkins.com.donutclub.data.services.firebase;

import ryanddawkins.com.donutclub.data.access.AuthAccess;
import ryanddawkins.com.donutclub.data.pojo.User;
import ryanddawkins.com.donutclub.data.services.AuthService;

/**
 * Created by ryan on 3/13/16.
 */
public class FirebaseAuthService implements AuthService {

    private AuthAccess authAccess;

    public FirebaseAuthService() {
        this.authAccess = authAccess;
    }

    @Override
    public boolean isSignedin() {
        return this.authAccess.isLoggedIn();
    }

    @Override
    public User getCurrentUser() {
        return this.authAccess.getCurrentUser();
    }

    @Override
    public void setCurrentUser(User user) {
        this.authAccess.setCurrentUser(user);
    }

    @Override
    public void authenticate(String token, String type) {
        this.authAccess.authenticate(token, type);
    }


}
