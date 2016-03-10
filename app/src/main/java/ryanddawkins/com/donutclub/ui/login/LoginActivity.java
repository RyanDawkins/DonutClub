package ryanddawkins.com.donutclub.ui.login;

import ryanddawkins.com.donutclub.R;
import ryanddawkins.com.donutclub.base.BaseActivity;

/**
 * Created by ryan on 3/6/16.
 */
public class LoginActivity extends BaseActivity {

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
}
