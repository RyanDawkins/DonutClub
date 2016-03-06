package ryanddawkins.com.donutclub.data.access.firebase;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ryanddawkins.com.donutclub.data.access.DataListenerAdapter;
import ryanddawkins.com.donutclub.data.access.RsvpListAccess;
import ryanddawkins.com.donutclub.data.access.RsvpListCallback;
import ryanddawkins.com.donutclub.data.formatters.EventDateFormatter;
import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/5/16.
 */
public class FirebaseRsvpListAccess implements RsvpListAccess {

    /**
     * This method makes a request to the
     * @param rsvpListCallback
     * @param eventDate
     * @return
     */
    @Override
    public DataListenerAdapter getRsvpList(final RsvpListCallback rsvpListCallback, Date eventDate) {

        Firebase baseRef = FirebaseUtil.getFirebaseBaseRef();

        EventDateFormatter eventDateFormatter = new EventDateFormatter(eventDate);
        Firebase ref = baseRef.child("rsvps/" + eventDateFormatter.formatMonthDayYear());

        ValueEventListener valueEventListener = ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<User> rsvpList = new ArrayList<User>((int) dataSnapshot.getChildrenCount());
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    rsvpList.add(childSnapshot.getValue(User.class));
                }

                rsvpListCallback.onGetList(rsvpList);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        return new FirebaseDataListener(ref, valueEventListener);
    }
}
