package ryanddawkins.com.donutclub.ui.login;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import ryanddawkins.com.donutclub.data.services.UserAccessService;
import ryanddawkins.com.donutclub.data.services.UserService;
import ryanddawkins.com.donutclub.ui.event.current.CurrentEventActivity;

/**
 * Created by ryan on 3/6/16.
 */
public class LoginActivity extends BaseActivity implements LoginView {

    private LoginPresenter loginPresenter;

    private static final int REQUEST_CODE_SMS_PERMISSION = 255;

    @Nullable
    @Bind(R.id.facebook_sign_in_btn)
    protected LoginButton facebookSigninBtn;

    @Nullable
    @Bind(R.id.google_sign_in_btn)
    protected SignInButton googleSigninBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setupGoogleBtn();

        UserService userService = new UserAccessService(new FirebaseUserAccess());

        this.loginPresenter = new LoginPresenter(this, DonutClub.getInstance().getAuthService(), userService);
        this.loginPresenter.setupSms();
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

        this.facebookSigninBtn.registerCallback(callbackManager, this.loginPresenter);
    }

    private void setupGoogleBtn() {
        if(this.googleSigninBtn == null) {
            return;
        }
    }

    public void onSetupSms() {
        setupFacebookBtn();
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

    @Override
    public boolean hasSmsPermission() {
        return ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showSmsPermissionDialog() {
        ActivityCompat.requestPermissions(LoginActivity.this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_SMS_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case REQUEST_CODE_SMS_PERMISSION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    this.loginPresenter.onSmsPermissionGranted();
                } else {
                    this.loginPresenter.onSmsPermissionDenied("READ_SMS Denied");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
