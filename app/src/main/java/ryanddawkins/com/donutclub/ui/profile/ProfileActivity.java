package ryanddawkins.com.donutclub.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import ryanddawkins.com.donutclub.R;
import ryanddawkins.com.donutclub.base.BaseActivity;
import ryanddawkins.com.donutclub.data.access.firebase.FirebaseUserAccess;
import ryanddawkins.com.donutclub.data.services.UserAccessService;
import ryanddawkins.com.donutclub.data.services.UserService;

/**
 * Created by ryan on 3/6/16.
 */
public class ProfileActivity extends BaseActivity implements ProfileView {

    @Nullable
    @Bind(R.id.user_name)
    protected TextView nameEditText;

    @Nullable
    @Bind(R.id.user_email)
    protected TextView emailEditText;

    @Nullable
    @Bind(R.id.user_phone)
    protected TextView phoneEditText;

    private ProfilePresenter profilePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.enableBackNav();

        // Eventually this will be handled with dependency injection.
        UserService userService = new UserAccessService(new FirebaseUserAccess());
        this.profilePresenter = new ProfilePresenter(this, userService);

        // Gets the username from the intent and then looks up the user.
        Intent intent = getIntent();
        this.profilePresenter.getUser(intent.getStringExtra("userId"));
    }

    /**
     * Overrides the layout resource.
     * @return
     */
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_profile;
    }

    /**
     * Sets the name into the edit text
     * @param name
     */
    @Override
    public void setName(String name) {
        if(this.nameEditText != null) {
            this.nameEditText.setText(name);
        }
    }

    /**
     * Sets up the phone number in the edit text
     * @param phone
     */
    @Override
    public void setPhone(String phone) {
        if(this.phoneEditText != null) {
            this.phoneEditText.setText(phone);
        }
    }

    /**
     * Sets the email into the edit text
     * @param email
     */
    @Override
    public void setEmail(String email) {
        if(this.emailEditText != null) {
            this.emailEditText.setText(email);
        }
    }

    @Nullable
    @OnClick(R.id.callBtn)
    public void onCallClicked() {
        this.profilePresenter.onCallBtnClicked();
    }

    @Override
    public void callNumber(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);
    }
}
