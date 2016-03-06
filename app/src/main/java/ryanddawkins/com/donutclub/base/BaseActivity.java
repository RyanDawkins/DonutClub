package ryanddawkins.com.donutclub.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import ryanddawkins.com.donutclub.R;

/**
 * Created by ryan on 3/3/16.
 */
public class BaseActivity extends AppCompatActivity {

    @Nullable
    @Bind(R.id.container)
    protected FrameLayout container;

    @Nullable
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    protected FragmentManager fragmentManager = null;

    protected boolean mIsBackNav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(this.getLayoutResource());

        ButterKnife.bind(this);

        if(this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        }

        if(this.fragmentManager == null) {
            this.fragmentManager = this.getFragmentManager();
        }

    }

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

    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    protected void addFragmentToContainer(Fragment fragment, String tag) {

        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, tag).commit();

    }



}
