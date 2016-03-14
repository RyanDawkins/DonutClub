package ryanddawkins.com.donutclub.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import ryanddawkins.com.donutclub.R;
import ryanddawkins.com.donutclub.data.services.FakeAuthService;
import ryanddawkins.com.donutclub.ui.login.LoginActivity;

/**
 * Created by ryan on 3/3/16.
 */
public class BaseActivity extends AppCompatActivity implements BaseActivityView {

    @Nullable
    @Bind(R.id.container)
    protected FrameLayout container;

    @Nullable
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    protected FragmentManager fragmentManager = null;
    private BaseActivityController baseActivityController;
    protected boolean mIsBackNav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FakeAuthService fakeAuthService = new FakeAuthService();
        this.baseActivityController = new BaseActivityController(this, fakeAuthService);

        // If we are not a login screen then check the auth.
        if(!this.isLoginScreen()) {
            this.baseActivityController.checkAuth();
        }

        this.setContentView(this.getLayoutResource());

        ButterKnife.bind(this);

        if(this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        }

        if(this.fragmentManager == null) {
            this.fragmentManager = this.getFragmentManager();
        }

    }

    /**
     * This method will unbind butterknife to prevent memory leaks.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * Helper method to enable Back navigation for a activity
     * When this method is called the activity will then display
     * a Back arrow that will navigate to the last item on the Stack
     */
    protected void enableBackNav() {
        if(getSupportActionBar() != null) {
            mIsBackNav = true;
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home && mIsBackNav) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method that should be overrode for a layout.
     * @return
     */
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    /**
     * Adds the fragment to the container layout.
     * @param fragment
     * @param tag
     */
    protected void addFragmentToContainer(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, tag).commit();
    }

    /**
     * Override this method to stop the login from redirecting if not signed in.
     * @return
     */
    protected boolean isLoginScreen() {
        return false;
    }

    /**
     * Navigates to the login screen.
     */
    @Override
    public void navigateToLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.startActivity(intent);
        this.finish();
    }
}
