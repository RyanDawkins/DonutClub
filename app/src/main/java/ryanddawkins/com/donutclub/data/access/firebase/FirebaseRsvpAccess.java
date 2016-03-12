package ryanddawkins.com.donutclub.data.access.firebase;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import ryanddawkins.com.donutclub.data.access.DataListenerAdapter;
import ryanddawkins.com.donutclub.data.access.GetUserCallback;
import ryanddawkins.com.donutclub.data.access.RsvpAccess;
import ryanddawkins.com.donutclub.data.access.UserAccess;

/**
 * Created by ryan on 3/5/16.
 */
public class FirebaseRsvpAccess implements RsvpAccess {

    /**
     * This method makes a request to the
     * @param getUserCallback
     * @param eventId
     * @return
     */
    @Override
    public DataListenerAdapter getRsvpList(final GetUserCallback getUserCallback, String eventId) {

        Firebase baseRef = FirebaseUtil.getFirebaseBaseRef();

        Firebase ref = baseRef.child("events").child(eventId).child("rsvps");

        final UserAccess userAccess = new FirebaseUserAccess();
        ValueEventListener valueEventListener = ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("snapshot", dataSnapshot.toString());
                Log.d("snapshotLength", ""+dataSnapshot.getChildrenCount());
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {

                    // This is because it is formatted like "ryan: true"
                    String username = childSnapshot.getKey();
                    userAccess.getUser(getUserCallback, username);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("firebase error", firebaseError.toString());
            }
        });

        return new FirebaseDataListener(ref, valueEventListener);
    }

    /**
     * This method will rsvp with the given date string
     * @param username
     * @param eventKeyString
     */
    @Override
    public void rsvpUser(String username, String eventKeyString) {
        Firebase baseRef = FirebaseUtil.getFirebaseBaseRef();
    }


}
