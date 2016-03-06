package ryanddawkins.com.donutclub.ui.event.current;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ryanddawkins.com.donutclub.R;
import ryanddawkins.com.donutclub.base.BaseFragment;
import ryanddawkins.com.donutclub.base.ItemCallback;
import ryanddawkins.com.donutclub.data.access.RsvpListAccess;
import ryanddawkins.com.donutclub.data.access.firebase.FirebaseRsvpListAccess;
import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/3/16.
 */
public class CurrentEventFragment extends BaseFragment implements CurrentEventView, ItemCallback {

    public static CurrentEventFragment newInstance() {

        Bundle args = new Bundle();

        CurrentEventFragment fragment = new CurrentEventFragment();
        fragment.setArguments(args);

        RsvpListAccess rsvpListAccess = new FirebaseRsvpListAccess();
        fragment.setController(new CurrentEventController(fragment, rsvpListAccess));

        return fragment;
    }

    private CurrentEventController currentEventController;
    private List<User> rsvpList;
    private CurrentEventAdapter currentEventAdapter;

    @Nullable
    @Bind(R.id.current_event_recycler)
    protected RecyclerView recyclerView;

    @Nullable
    @Bind(R.id.when_date)
    protected TextView whenDateTextView;

    @Nullable
    @Bind(R.id.where_location)
    protected TextView whereLocationTextView;

    @Nullable
    @Bind(R.id.rsvp_count)
    protected TextView rsvpCountTextView;

    @Nullable
    @Bind(R.id.deadline_to_rsvp)
    protected TextView deadlineToRsvpTextView;

    @Override
    @LayoutRes
    public int getLayout() {
        return R.layout.current_event_recycler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        if(this.recyclerView != null) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            this.recyclerView.setLayoutManager(layoutManager);

            RecyclerViewHeader recyclerViewHeader = RecyclerViewHeader.fromXml(this.getActivity(), R.layout.event_current_header);
            recyclerViewHeader.attachTo(this.recyclerView);

            ButterKnife.bind(this, recyclerViewHeader);
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.currentEventAdapter = new CurrentEventAdapter(this);
        if(this.recyclerView != null) {
            this.recyclerView.setAdapter(this.currentEventAdapter);
        }

        this.currentEventController.loadRsvpList();
    }

    public void setController(CurrentEventController currentEventController) {
        this.currentEventController = currentEventController;
    }

    @Nullable
    @OnClick(R.id.rsvpButton)
    public void rsvpClick() {
        this.currentEventController.handleRsvpClick();
    }

    public void setWhenDate(String whenDate) {
        if(this.whenDateTextView != null) {
            this.whenDateTextView.setText(whenDate);
        }
    }

    @Override
    public void setWhereLocation(String whereLocation) {
        if(this.whereLocationTextView != null) {
            this.whereLocationTextView.setText(whereLocation);
        }
    }

    @Override
    public void setRsvpCount(String rsvpCount) {
        if(this.rsvpCountTextView != null) {
            this.rsvpCountTextView.setText(rsvpCount);
        }
    }

    @Override
    public void setDeadlineToRsvp(String deadlineToRsvp) {
        if(this.deadlineToRsvpTextView != null) {
            this.deadlineToRsvpTextView.setText(deadlineToRsvp);
        }
    }

    @Override
    public void setRsvpList(List<User> rsvpList) {
        this.rsvpList = rsvpList;

        this.currentEventAdapter.setRsvpList(rsvpList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.currentEventController = null;
    }

    @Override
    public void onItemClick(int position) {
        User user = this.rsvpList.get(position);
        this.currentEventController.onUserSelected(user);
    }
}
