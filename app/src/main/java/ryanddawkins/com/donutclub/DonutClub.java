package ryanddawkins.com.donutclub;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by ryan on 3/3/16.
 */
public class DonutClub extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
