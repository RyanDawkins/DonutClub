package ryanddawkins.com.donutclub;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.firebase.client.Firebase;

import ryanddawkins.com.donutclub.data.services.AuthService;
import ryanddawkins.com.donutclub.data.services.firebase.FirebaseAuthService;

/**
 * Created by ryan on 3/3/16.
 */
public class DonutClub extends Application {

    private static DonutClub instance;
    private AuthService authService;

    public static DonutClub getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        FacebookSdk.sdkInitialize(this);

        instance = this;
    }

    public AuthService getAuthService() {
        if(this.authService == null) {
            this.authService = new FirebaseAuthService();
        }
        return authService;
    }

}
