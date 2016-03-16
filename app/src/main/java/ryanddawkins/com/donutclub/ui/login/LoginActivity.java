package ryanddawkins.com.donutclub.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;

import butterknife.Bind;
import ryanddawkins.com.donutclub.DonutClub;
import ryanddawkins.com.donutclub.R;
import ryanddawkins.com.donutclub.base.BaseActivity;
import ryanddawkins.com.donutclub.data.access.firebase.FirebaseUserAccess;
import ryanddawkins.com.donutclub.data.services.AuthService;
import ryanddawkins.com.donutclub.data.services.FacebookSigninService;
import ryanddawkins.com.donutclub.data.services.UserAccessService;
import ryanddawkins.com.donutclub.data.services.UserService;
import ryanddawkins.com.donutclub.ui.event.current.CurrentEventActivity;

/**
 * Created by ryan on 3/6/16.
 */
public class LoginActivity extends BaseActivity implements LoginView {

    private LoginPresenter loginPresenter;

    @Nullable
    @Bind(R.id.facebook_sign_in_btn)
    protected LoginButton facebookSigninBtn;

    @Nullable
    @Bind(R.id.google_sign_in_btn)
    protected SignInButton googleSigninBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setupFacebookBtn();
        this.setupGoogleBtn();

        UserService userService = new UserAccessService(new FirebaseUserAccess());

        this.loginPresenter = new LoginPresenter(this, DonutClub.getInstance().getAuthService(), userService);
    }

    /**
     * Returns the login layout.
     * @return
     */
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    /**
     * This will prevent it from trying to keep navigate to the login screen
     * @return
     */
    @Override
    protected boolean isLoginScreen() {
        return true;
    }

    private void setupFacebookBtn() {

        if(this.facebookSigninBtn == null) {
            return;
        }

        CallbackManager callbackManager = CallbackManager.Factory.create();
        AuthService authService = DonutClub.getInstance().getAuthService();
        FacebookSigninService facebookSigninService = new FacebookSigninService(authService, this.loginPresenter, getPhoneNumber());

        this.facebookSigninBtn.setReadPermissions("email");
        this.facebookSigninBtn.setReadPermissions("public_profile");
        this.facebookSigninBtn.registerCallback(callbackManager, facebookSigninService);
    }

    private void setupGoogleBtn() {

        if(this.googleSigninBtn == null) {
            return;
        }

    }

    @Override
    public void navigateToCurrentEvent(String groupId) {
        Intent intent = new Intent(this, CurrentEventActivity.class);
        intent.putExtra(CurrentEventActivity.GROUP_ID, groupId);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public String getPhoneNumber() {
        return ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
                .getLine1Number();
    }

    @Override
    public void showMessage(String message) {
        showSnackbar(message);
    }

}
