package ryanddawkins.com.donutclub.data.access.firebase;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import ryanddawkins.com.donutclub.data.access.GetUserCallback;
import ryanddawkins.com.donutclub.data.access.UserAccess;
import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/6/16.
 */
public class FirebaseUserAccess implements UserAccess {

    /**
     * This method takes a username and looks up the user in firebase and then sends it back through
     * the callback. It will not attach and listen for new changes to the user object.
     * @param getUserCallback
     * @param username
     */
    @Override
    public void getUser(final GetUserCallback getUserCallback, String username) {

        Firebase baseRef = FirebaseUtil.getFirebaseBaseRef();
        baseRef.child("/users/"+username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getUserCallback.onUserRetrieved(dataSnapshot.getValue(User.class));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
