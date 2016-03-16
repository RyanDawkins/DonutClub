package ryanddawkins.com.donutclub.data.services;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by ryan on 3/13/16.
 */
public class GPlusSigninService implements SigninService, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private AppCompatActivity appCompatActivity;
    private GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener;

    public GPlusSigninService(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
