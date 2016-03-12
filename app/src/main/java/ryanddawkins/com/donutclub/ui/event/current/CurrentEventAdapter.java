package ryanddawkins.com.donutclub.ui.event.current;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ryanddawkins.com.donutclub.R;
import ryanddawkins.com.donutclub.base.ItemCallback;
import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/3/16.
 */
public class CurrentEventAdapter extends RecyclerView.Adapter<CurrentEventsViewHolder> {

    private List<User> rsvpList;
    private ItemCallback itemCallback;

    public CurrentEventAdapter(ItemCallback itemCallback) {
        this.rsvpList = new ArrayList<User>();
        this.itemCallback = itemCallback;
    }

    @Override
    public CurrentEventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.current_event_item_holder, parent, false);

        CurrentEventsViewHolder viewHolder = new CurrentEventsViewHolder(view, this.itemCallback);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CurrentEventsViewHolder holder, int position) {

        User user = this.rsvpList.get(position);

        holder.bind(user);

        Log.d("binding", user.getFirstName());
    }

    @Override
    public int getItemCount() {
        return this.rsvpList.size();
    }

    public void setRsvpList(List<User> rsvpList) {
        this.rsvpList = rsvpList;

        this.notifyDataSetChanged();
    }



}
