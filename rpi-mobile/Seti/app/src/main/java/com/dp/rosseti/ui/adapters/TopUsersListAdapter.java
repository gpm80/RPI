package com.dp.rosseti.ui.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dp.rosseti.R;
import com.dp.rosseti.data.db.entities.TopUser;

import java.util.List;

public class TopUsersListAdapter extends RecyclerView.Adapter<TopUsersListAdapter.TopUsersViewHolder> {

    class TopUsersViewHolder extends RecyclerView.ViewHolder {
        private final TextView topUserItemView;
        private final TextView chartLineItemView;
        private final TextView ratingItemView;
        private final ImageView topUserAvatarItemView;

        private TopUsersViewHolder(View itemView) {
            super(itemView);
            topUserItemView = itemView.findViewById(R.id.topuser_name);
            topUserAvatarItemView = itemView.findViewById(R.id.topuser_avatar);
            chartLineItemView = itemView.findViewById(R.id.tv_chart_line);
            ratingItemView = itemView.findViewById(R.id.topuser_rating);
        }
    }

    private final LayoutInflater mInflater;
    private List<TopUser> mTopUsers; // Cached copy of users

    public TopUsersListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public TopUsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.topuser_recyclerview_item, parent, false);
        return new TopUsersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TopUsersViewHolder holder, int position) {
        TopUser current = mTopUsers.get(position);
        holder.topUserItemView.setText(current.getTopUser());
        holder.topUserAvatarItemView.setImageURI(Uri.parse(current.getAvatar()));
        holder.chartLineItemView.setText(Integer.toString(current.getChartLine()) + ".");
        holder.ratingItemView.setText(Integer.toString(current.getRating()));
    }

    public void setTopUsers(List<TopUser> topUsers){
        mTopUsers = topUsers;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mTopUsers != null)
            return mTopUsers.size();
        else return 0;
    }
}