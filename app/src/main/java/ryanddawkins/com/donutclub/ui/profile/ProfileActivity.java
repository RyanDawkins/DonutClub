package ryanddawkins.com.donutclub.ui.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ryanddawkins.com.donutclub.R;
import ryanddawkins.com.donutclub.base.BaseActivity;

/**
 * Created by ryan on 3/6/16.
 */
public class ProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.enableBackNav();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_profile;
    }
}
