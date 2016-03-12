package ryanddawkins.com.donutclub.ui.event.current;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ryanddawkins.com.donutclub.R;
import ryanddawkins.com.donutclub.base.ItemCallback;
import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/3/16.
 */
public class CurrentEventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private User user;
    private ItemCallback itemCallback;

    @Nullable
    @Bind(R.id.user_name)
    protected TextView user_name;

    public CurrentEventsViewHolder(View itemView, ItemCallback itemCallback) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        this.itemCallback = itemCallback;
    }

    public void bind(User user) {
        this.user = user;
        this.user_name.setText(user.getFirstName() + " " + user.getLastName());
    }

    @Override
    public void onClick(View v) {
        this.itemCallback.onItemClick(this.getAdapterPosition());
    }
}
