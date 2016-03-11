package ryanddawkins.com.donutclub.data.access.firebase;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import ryanddawkins.com.donutclub.data.access.EventAccess;
import ryanddawkins.com.donutclub.data.access.EventCallback;

/**
 * Created by ryan on 3/10/16.
 */
public class FirebaseEventCallback implements EventAccess {

    @Override
    public void getCurrentEvent(EventCallback eventCallback, String groupId) {
        Firebase baseRef = FirebaseUtil.getFirebaseBaseRef();
        baseRef.child(groupId+"/events").orderByKey().limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("snapshot", dataSnapshot.toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
}
