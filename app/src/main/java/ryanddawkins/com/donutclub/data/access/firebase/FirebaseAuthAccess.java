package ryanddawkins.com.donutclub.data.access.firebase;

import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import ryanddawkins.com.donutclub.data.access.AuthAccess;
import ryanddawkins.com.donutclub.data.access.AuthCallback;
import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/13/16.
 */
public class FirebaseAuthAccess implements AuthAccess, Firebase.AuthResultHandler {

    private String type;
    private AuthCallback authCallback;
    private User currentUser;

    public FirebaseAuthAccess(AuthCallback authCallback, String type) {
        this.type = type;
        this.authCallback = authCallback;
    }

    @Override
    public void authenticate(String token) {
        Firebase ref = FirebaseUtil.getFirebaseBaseRef();
        ref.authWithOAuthToken(this.type, token, this);
    }

    @Override
    public boolean isLoggedIn() {
        Firebase ref = FirebaseUtil.getFirebaseBaseRef();
        return ref.getAuth() != null;
    }

    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    @Override
    public void onAuthenticated(AuthData authData) {
        String userId = authData.getUid();
        this.authCallback.onAuthSuccess(userId);
    }

    @Override
    public void onAuthenticationError(FirebaseError firebaseError) {
        Log.e("firebaseAuthError", firebaseError.getDetails());
        this.authCallback.onAuthError(firebaseError.getMessage());
    }

    @Override
    public void saveCurrentUser(String userId) {
        this.currentUser.setId(userId);
        Firebase userRef = FirebaseUtil.getFirebaseBaseRef().child("users").child(userId);
        userRef.setValue(this.getCurrentUser());
    }
}
