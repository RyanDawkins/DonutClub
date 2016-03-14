package ryanddawkins.com.donutclub.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import ryanddawkins.com.donutclub.R;
import ryanddawkins.com.donutclub.base.BaseActivity;
import ryanddawkins.com.donutclub.data.access.firebase.FirebaseAuthAccess;
import ryanddawkins.com.donutclub.data.services.FirebaseAuthService;
import ryanddawkins.com.donutclub.ui.event.current.CurrentEventActivity;

/**
 * Created by ryan on 3/6/16.
 */
public class LoginActivity extends BaseActivity implements FacebookCallback<LoginResult>, LoginView {

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

        this.loginPresenter = new LoginPresenter(this, new FirebaseAuthService());
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

        this.facebookSigninBtn.setReadPermissions("email");
        this.facebookSigninBtn.setReadPermissions("public_profile");
        this.facebookSigninBtn.registerCallback(callbackManager, this);
    }

    private void setupGoogleBtn() {

        if(this.googleSigninBtn == null) {
            return;
        }



    }

    @Override
    public void onSuccess(final LoginResult loginResult) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                if(response.getError() != null) {
                    Log.e("graphRequestError", response.toString());
                }

                String email;
                try {
                    email = object.getString("email");
                } catch(JSONException ex) {
                    email = null;
                }

                loginPresenter.onLoginSuccess(new FacebookLoginSuccessAdapter(loginResult, email, getPhoneNumber()));
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "email");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    @Override
    public void onCancel() {
    }

    @Override
    public void onError(FacebookException error) {
        this.loginPresenter.onLoginError(new FacebookLoginErrorAdapter(error));
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

    }
}
