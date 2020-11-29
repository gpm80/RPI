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
import com.dp.rosseti.data.db.entities.ShortIdea;
import com.dp.rosseti.data.db.entities.User;

import java.util.List;

public class ShortIdeasListAdapter extends RecyclerView.Adapter<ShortIdeasListAdapter.UsersViewHolder> {

    class UsersViewHolder extends RecyclerView.ViewHolder {
        private final TextView shortIdeaTitle;
        private final TextView shortIdeaDescription;
        //private final ImageView shortIdeaLogo;

        private UsersViewHolder(View itemView) {
            super(itemView);
            shortIdeaTitle = itemView.findViewById(R.id.shortidea_title);
            shortIdeaDescription = itemView.findViewById(R.id.shortidea_description);
            //shortIdeaLogo = itemView.findViewById(R.id.shortidea_logo);
        }
    }

    private final LayoutInflater mInflater;
    private List<ShortIdea> mShortIdeas; // Cached copy of users

    public ShortIdeasListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.shortidea_recyclerview_item, parent, false);
        return new UsersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {
        ShortIdea current = mShortIdeas.get(position);
        holder.shortIdeaTitle.setText(current.getTitle());
        holder.shortIdeaDescription.setText(current.getDescription());
        //holder.shortIdeaLogo.setImageURI(Uri.parse(current.getAttachment()));
    }

    public void setShortIdea(List<ShortIdea> shortIdeas){
        mShortIdeas = shortIdeas;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mShortIdeas != null)
            return mShortIdeas.size();
        else return 0;
    }
}