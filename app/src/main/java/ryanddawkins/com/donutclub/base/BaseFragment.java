package ryanddawkins.com.donutclub.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by ryan on 3/3/16.
 */
public abstract class BaseFragment extends Fragment {

    private View parentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.parentView = inflater.inflate(this.getLayout(), container, false);

        if(this.parentView != null) {
            ButterKnife.bind(this, this.parentView);
        }

        return this.parentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.unbind(this);
    }

    @LayoutRes public abstract int getLayout();

    public void showSnackbar(String text) {
        Snackbar.make(this.parentView, text, Snackbar.LENGTH_SHORT).show();
    }

}
