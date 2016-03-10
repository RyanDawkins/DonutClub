package ryanddawkins.com.donutclub.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;
import ryanddawkins.com.donutclub.R;
import ryanddawkins.com.donutclub.base.BaseActivity;
import ryanddawkins.com.donutclub.ui.event.current.CurrentEventFragment;

public class MainActivity extends BaseActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "JrAFdVUT3zbAhn0Cxh47pkaRz";
    private static final String TWITTER_SECRET = "BFt0xpvLUfG5B3z0bMATA70eV9riyMk4e8X4TwisODGH45rJki";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits());

        CurrentEventFragment currentEventFragment = CurrentEventFragment.newInstance();
        this.addFragmentToContainer(currentEventFragment, null);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
