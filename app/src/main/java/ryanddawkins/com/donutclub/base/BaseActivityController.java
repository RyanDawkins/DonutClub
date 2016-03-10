package ryanddawkins.com.donutclub.base;

import ryanddawkins.com.donutclub.data.services.AuthService;

/**
 * Created by ryan on 3/6/16.
 */
public class BaseActivityController {

    private BaseActivityView baseActivityView;
    private AuthService authService;

    public BaseActivityController(BaseActivityView baseActivityView, AuthService authService) {
        this.baseActivityView = baseActivityView;
        this.authService = authService;
    }

    public void checkAuth() {
        if(!this.authService.isSignedin()) {
            this.baseActivityView.navigateToLoginScreen();
        }
    }

}
