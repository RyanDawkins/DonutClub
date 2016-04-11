package ryanddawkins.com.donutclub.ui.login;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

import ryanddawkins.com.donutclub.data.access.AuthCallback;
import ryanddawkins.com.donutclub.data.pojo.User;
import ryanddawkins.com.donutclub.data.services.AuthService;
import ryanddawkins.com.donutclub.data.services.UserService;

/**
 * Created by ryan on 3/13/16.
 */
public class LoginPresenter implements FacebookCallback<LoginResult>, AuthCallback {

    private LoginView loginView;
    private AuthService authService;
    private UserService userService;

    public LoginPresenter(LoginView loginView, AuthService authService, UserService userService) {
        this.loginView = loginView;
        this.authService = authService;
        this.userService = userService;
    }

    @Override
    public void onAuthSuccess(String userId) {
        User currentUser = this.authService.getCurrentUser();
        this.userService.saveUser(currentUser, userId);
    }

    @Override
    public void onAuthError(String message) {

    }

    public void setupSms() {
        if(!this.loginView.hasSmsPermission()) {
            this.loginView.showSmsPermissionDialog();
        } else {
            this.onSmsPermissionGranted();
        }
    }

    public void onSmsPermissionGranted() {
        this.loginView.onSetupSms();
    }

    public void onSmsPermissionDenied(String message) {
        this.loginView.showMessage(message);
    }


    @Override
    public void onSuccess(LoginResult loginResult) {
        String token = loginResult.getAccessToken().getToken();
        this.authService.authenticate(token, "");
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }
}
