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
    }

    public void onLoginSuccess(LoginSuccessAdapter loginSuccessAdapter) {
        this.authService.setCurrentUser(loginSuccessAdapter.getUser());

    }

    public void onLoginError(LoginErrorAdapter loginErrorAdapter) {

    }

    /**
     * Methods from the auth callback for the authService stuff
     * @param userId
     */
    @Override
    public void onAuthSuccess(String userId) {

    }

    /**
     * Methods passed into the auth service for callback
     * @param message
     */
    @Override
    public void onAuthError(String message) {

    }
}
