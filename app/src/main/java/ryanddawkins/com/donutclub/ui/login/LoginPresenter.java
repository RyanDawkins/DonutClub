package ryanddawkins.com.donutclub.ui.login;

import ryanddawkins.com.donutclub.data.services.AuthService;

/**
 * Created by ryan on 3/13/16.
 */
public class LoginPresenter {

    private LoginView loginView;

    public LoginPresenter(LoginView loginView, AuthService authService) {
        this.loginView = loginView;
    }

    public void onLoginSuccess(LoginSuccessAdapter loginSuccessAdapter) {

    }

    public void onLoginError() {

    }

}
