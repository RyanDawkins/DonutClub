package ryanddawkins.com.donutclub.data.services;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import ryanddawkins.com.donutclub.data.access.AuthCallback;
import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/14/16.
 */
public class FacebookSigninService implements FacebookCallback<LoginResult> {

    private AuthService authService;
    private AuthCallback authCallback;
    private String phoneNumber;

    public FacebookSigninService(AuthService authService, AuthCallback authCallback, String phoneNumber) {
        this.authService= authService;
        this.authCallback = authCallback;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        final AccessToken accessToken = loginResult.getAccessToken();

        Profile profile = Profile.getCurrentProfile();

        final User user = new User();
        user.setPhoneNumber(this.phoneNumber);
        user.setFirstName(profile.getFirstName());
        user.setLastName(profile.getLastName());

        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                String email;
                try {
                    email = object.getString("email");
                    user.setEmail(email);
                    authService.setCurrentUser(user);
                    authService.authenticate(accessToken.getToken());

                } catch (JSONException e) {
                    authCallback.onAuthError(e.getMessage());
                }
            }
        });
    }

    @Override
    public void onCancel() {
        this.authCallback.onAuthError("Request was cancelled");
    }

    @Override
    public void onError(FacebookException error) {
        this.authCallback.onAuthError(error.getMessage());
    }
}
