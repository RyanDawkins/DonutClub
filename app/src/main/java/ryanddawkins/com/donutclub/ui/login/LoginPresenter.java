package ryanddawkins.com.donutclub.ui.login;

import ryanddawkins.com.donutclub.data.access.AuthCallback;
import ryanddawkins.com.donutclub.data.services.AuthService;

/**
 * Created by ryan on 3/13/16.
 */
public class LoginPresenter implements AuthCallback {

    private LoginView loginView;
    private AuthService authService;

    public LoginPresenter(LoginView loginView, AuthService authService) {
        this.loginView = loginView;
        this.authService = authService;
    }

    @Override
    public void onAuthSuccess(String userId) {

    }

    @Override
    public void onAuthError(String message) {

    }
}
