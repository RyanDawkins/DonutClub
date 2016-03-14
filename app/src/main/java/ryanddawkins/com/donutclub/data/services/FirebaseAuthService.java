package ryanddawkins.com.donutclub.data.services;

import com.firebase.client.Firebase;

import ryanddawkins.com.donutclub.data.access.firebase.FirebaseUtil;
import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/13/16.
 */
public class FirebaseAuthService implements AuthService {

    private User currentUser;

    @Override
    public boolean isSignedin() {
        Firebase ref = FirebaseUtil.getFirebaseBaseRef();

        return ref.getAuth() != null;
    }

    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public void setCurrentUser(User user) {
        this.currentUser = currentUser;
    }
}
