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
import com.dp.rosseti.data.db.entities.User;

import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UsersViewHolder> {

    class UsersViewHolder extends RecyclerView.ViewHolder {
        private final TextView userItemView;
        private final TextView positionItemView;
        private final ImageView avatarItemView;

        private UsersViewHolder(View itemView) {
            super(itemView);
            userItemView = itemView.findViewById(R.id.textView);
            positionItemView = itemView.findViewById(R.id.textView2);
            avatarItemView = itemView.findViewById(R.id.user_avatar);
        }
    }

    private final LayoutInflater mInflater;
    private List<User> mUsers; // Cached copy of users

    public UsersListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.user_recyclerview_item2, parent, false);
        return new UsersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {
        User current = mUsers.get(position);
        holder.userItemView.setText(current.getUser());
        holder.positionItemView.setText(current.getPosition());
        holder.avatarItemView.setImageURI(Uri.parse(current.getAvatar()));
    }

    public void setUsers(List<User> users){
        mUsers = users;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mUsers != null)
            return mUsers.size();
        else return 0;
    }
}