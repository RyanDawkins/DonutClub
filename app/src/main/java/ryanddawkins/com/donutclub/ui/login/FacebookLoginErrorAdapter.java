package ryanddawkins.com.donutclub.ui.login;

import com.facebook.FacebookException;

/**
 * Created by ryan on 3/13/16.
 */
public class FacebookLoginErrorAdapter implements LoginErrorAdapter {

    private FacebookException facebookException;

    public FacebookLoginErrorAdapter(FacebookException facebookException) {
        this.facebookException = facebookException;
    }

    @Override
    public String onError() {
        return this.facebookException.getMessage();
    }
}
