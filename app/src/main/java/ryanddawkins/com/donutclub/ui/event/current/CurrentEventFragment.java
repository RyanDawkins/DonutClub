package ryanddawkins.com.donutclub.ui.event.current;

import android.content.Intent;
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
import ryanddawkins.com.donutclub.data.access.RsvpAccess;
import ryanddawkins.com.donutclub.data.access.firebase.FirebaseRsvpAccess;
import ryanddawkins.com.donutclub.data.pojo.User;
import ryanddawkins.com.donutclub.data.services.AuthService;
import ryanddawkins.com.donutclub.data.services.CurrentEventDateService;
import ryanddawkins.com.donutclub.data.services.FakeAuthService;
import ryanddawkins.com.donutclub.data.services.FakeCurrentEventDateService;
import ryanddawkins.com.donutclub.data.services.RsvpAccessService;
import ryanddawkins.com.donutclub.data.services.RsvpService;
import ryanddawkins.com.donutclub.ui.profile.ProfileActivity;

/**
 * Created by ryan on 3/3/16.
 */
public class CurrentEventFragment extends BaseFragment implements CurrentEventView, ItemCallback {

    /**
     * Returns a static instance. This sets up the fragment.
     * @return
     */
    public static CurrentEventFragment newInstance() {

        Bundle args = new Bundle();

        CurrentEventFragment fragment = new CurrentEventFragment();
        fragment.setArguments(args);

        RsvpAccess rsvpAccess = new FirebaseRsvpAccess();
        RsvpService rsvpService = new RsvpAccessService(rsvpAccess);
        AuthService authService = new FakeAuthService();
        CurrentEventDateService currentEventDateService = new FakeCurrentEventDateService();
        fragment.setPresenter(new CurrentEventPresenter(fragment, rsvpService, authService, currentEventDateService));

        return fragment;
    }

    private CurrentEventPresenter currentEventPresenter;
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

        if(this.currentEventPresenter != null) {
            this.currentEventPresenter.loadRsvpList();
        }
    }

    /**
     * This method is how we inject the controller into the fragment
     * @param currentEventPresenter
     */
    public void setPresenter(CurrentEventPresenter currentEventPresenter) {
        this.currentEventPresenter = currentEventPresenter;
    }

    /**
     * Method to handle the rsvp for the current user.
     */
    @Nullable
    @OnClick(R.id.rsvpButton)
    public void rsvpClick() {
        this.currentEventPresenter.handleRsvpClick();
    }

    public void setWhenDate(String whenDate) {
        if(this.whenDateTextView != null) {
            this.whenDateTextView.setText(whenDate);
        }
    }

    /**
     * Method to set the where location
     * @param whereLocation
     */
    @Override
    public void setWhereLocation(String whereLocation) {
        if(this.whereLocationTextView != null) {
            this.whereLocationTextView.setText(whereLocation);
        }
    }

    /**
     * Loads the rsvp count into the view
     * @param rsvpCount
     */
    @Override
    public void setRsvpCount(String rsvpCount) {
        if(this.rsvpCountTextView != null) {
            this.rsvpCountTextView.setText(rsvpCount);
        }
    }

    /**
     * Sets the deadline that they have to rsvp
     * @param deadlineToRsvp
     */
    @Override
    public void setDeadlineToRsvp(String deadlineToRsvp) {
        if(this.deadlineToRsvpTextView != null) {
            this.deadlineToRsvpTextView.setText(deadlineToRsvp);
        }
    }

    /**
     * Sets the current people in the rsvp list.
     * @param rsvpList
     */
    @Override
    public void setRsvpList(List<User> rsvpList) {
        this.rsvpList = rsvpList;

        this.currentEventAdapter.setRsvpList(rsvpList);
    }

    /**
     * unbinds the controller from the view
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.currentEventPresenter = null;
    }

    /**
     * Sends the user over to the controller to open the users profile page
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        User user = this.rsvpList.get(position);
        this.currentEventPresenter.onUserSelected(user);
    }

    /**
     * Navigates to the profile and passes the username of the user selected.
     * @param username
     */
    @Override
    public void navigateToProfile(String username) {
        Intent intent = new Intent(this.getActivity(), ProfileActivity.class);
        intent.putExtra("username", username);
        this.startActivity(intent);
    }

    @Override
    public String getGroupFromIntent() {

        // Eventually this will be the way we get the token.
        Intent intent = this.getActivity().getIntent();
        String groupId ="-KCYvxDqsMWhPkf8IM8J";//intent.getStringExtra("groupId");

        return groupId;
    }
}
