package ryanddawkins.com.donutclub.data.access.firebase;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Date;

import ryanddawkins.com.donutclub.data.access.DataListenerAdapter;
import ryanddawkins.com.donutclub.data.access.GetUserCallback;
import ryanddawkins.com.donutclub.data.access.RsvpAccess;
import ryanddawkins.com.donutclub.data.access.UserAccess;
import ryanddawkins.com.donutclub.data.formatters.EventDateFormatter;
import ryanddawkins.com.donutclub.data.pojo.Rsvp;

/**
 * Created by ryan on 3/5/16.
 */
public class FirebaseRsvpAccess implements RsvpAccess {

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

    /**
     * This method will rsvp with the given date string
     * @param rsvp
     * @param eventDateString
     */
    @Override
    public void rsvpUser(Rsvp rsvp, String eventDateString) {
        Firebase baseRef = FirebaseUtil.getFirebaseBaseRef();
        baseRef.child("/rsvps/"+eventDateString).setValue(rsvp);
    }
}
