package ryanddawkins.com.donutclub.data.services;

import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by ryan on 3/13/16.
 */
public class GPlusSigninService implements SigninService {

    private GoogleApiClient mGoogleApiClient;
    private AppCompatActivity appCompatActivity;
    private GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener;

    public GPlusSigninService(AppCompatActivity appCompatActivity, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.appCompatActivity = appCompatActivity;
        this.onConnectionFailedListener = onConnectionFailedListener;
    }

    @Override
    public void signin() {
        mGoogleApiClient = new GoogleApiClient.Builder(this.appCompatActivity)
                .enableAutoManage(this.appCompatActivity,
                        this.onConnectionFailedListener)
//                .addScope(Scopes.PLUS_LOGIN)
//                .addScope(Scopes.PLUS_ME)
                .build();
    }
}
