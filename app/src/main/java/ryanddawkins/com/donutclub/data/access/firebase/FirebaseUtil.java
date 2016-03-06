package ryanddawkins.com.donutclub.data.access.firebase;

import com.firebase.client.Firebase;

/**
 * Created by ryan on 3/5/16.
 */
public class FirebaseUtil {

    private static Firebase firebaseBaseRef;

    public static Firebase getFirebaseBaseRef() {

        if(firebaseBaseRef == null) {
            firebaseBaseRef = new Firebase("https://donut-club.firebaseio.com/");
        }
        return firebaseBaseRef;
    }

}
