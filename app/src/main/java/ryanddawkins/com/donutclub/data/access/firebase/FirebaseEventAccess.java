package ryanddawkins.com.donutclub.data.access.firebase;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import ryanddawkins.com.donutclub.data.access.EventAccess;
import ryanddawkins.com.donutclub.data.access.EventCallback;
import ryanddawkins.com.donutclub.data.pojo.Event;

/**
 * Created by ryan on 3/10/16.
 */
public class FirebaseEventAccess implements EventAccess {

    @Override
    public void getCurrentEvent(final EventCallback eventCallback, String groupId) {
        Firebase baseRef = FirebaseUtil.getFirebaseBaseRef();
        baseRef.child("groups/"+groupId+"/events").orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    String eventId = child.getKey();
                    getEvent(eventCallback, eventId);
                    return;
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    @Override
    public void getEvent(final EventCallback eventCallback, String eventId) {
        Firebase baseRef = FirebaseUtil.getFirebaseBaseRef();
        baseRef.child("events").child(eventId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Event event = dataSnapshot.getValue(Event.class);
                event.setEventId(dataSnapshot.getKey());
                eventCallback.onRetrievedEvent(event);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
}
