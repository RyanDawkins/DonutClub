package ryanddawkins.com.donutclub.data.access.firebase;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Date;

import ryanddawkins.com.donutclub.data.access.DataListenerAdapter;
import ryanddawkins.com.donutclub.data.access.GetUserCallback;
import ryanddawkins.com.donutclub.data.access.RsvpListAccess;
import ryanddawkins.com.donutclub.data.access.UserAccess;
import ryanddawkins.com.donutclub.data.formatters.EventDateFormatter;
import ryanddawkins.com.donutclub.data.pojo.Rsvp;

/**
 * Created by ryan on 3/5/16.
 */
public class FirebaseRsvpListAccess implements RsvpListAccess {

    /**
     * This method makes a request to the
     * @param getUserCallback
     * @param eventDate
     * @return
     */
    @Override
    public DataListenerAdapter getRsvpList(final GetUserCallback getUserCallback, Date eventDate) {

        Firebase baseRef = FirebaseUtil.getFirebaseBaseRef();

        EventDateFormatter eventDateFormatter = new EventDateFormatter(eventDate);
        Firebase ref = baseRef.child("rsvps/" + eventDateFormatter.formatMonthDayYear());

        ValueEventListener valueEventListener = ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Rsvp rsvp = childSnapshot.getValue(Rsvp.class);

                    UserAccess userAccess = new FirebaseUserAccess();
                    userAccess.getUser(getUserCallback, rsvp.getUsername());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        return new FirebaseDataListener(ref, valueEventListener);
    }
}
