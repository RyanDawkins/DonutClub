package ryanddawkins.com.donutclub.ui.login;

import com.facebook.Profile;
import com.facebook.login.LoginResult;

import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/13/16.
 */
public class FacebookLoginSuccessAdapter implements LoginSuccessAdapter {

    private LoginResult loginResult;
    private String phoneNumber;
    private String email;

    public FacebookLoginSuccessAdapter(LoginResult loginResult, String email, String phoneNumber) {
        this.loginResult = loginResult;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getToken() {
        return this.loginResult.getAccessToken().getToken();
    }

    @Override
    public User getUser() {

        Profile profile = Profile.getCurrentProfile();
        User user = new User();

        user.setFirstName(profile.getFirstName());
        user.setLastName(profile.getLastName());
        user.setEmail(this.email);
        user.setPhoneNumber(this.phoneNumber);

        return user;
    }
}
