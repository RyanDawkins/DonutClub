package ryanddawkins.com.donutclub.data.access.firebase;

import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;

import ryanddawkins.com.donutclub.data.access.DataListenerAdapter;
import ryanddawkins.com.donutclub.data.access.InvalidDataListenerCancelException;

/**
 * Created by ryan on 3/5/16.
 */
public class FirebaseDataListener implements DataListenerAdapter {

    private Firebase ref;
    private ValueEventListener valueEventListener;

    /**
     * This is built so that we can cancel value listeners.
     * @param ref the parent reference of the event listener
     * @param valueEventListener this is the ongoing event listener
     */
    public FirebaseDataListener(Firebase ref, ValueEventListener valueEventListener) {
        this.ref = ref;
        this.valueEventListener = valueEventListener;
    }

    /**
     * This will deattach the listener to prevent memory leaks.
     */
    @Override
    public void stopListening() {

        if(this.ref == null || this.valueEventListener == null) {
            throw new InvalidDataListenerCancelException("The reference or the valueEventListener is null. Have you already cancelled this event?");
        }

        this.ref.removeEventListener(this.valueEventListener);
    }

}
