package com.dp.rosseti.ui.topusers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dp.rosseti.R;
import com.dp.rosseti.data.db.entities.TopUser;
import com.dp.rosseti.data.db.entities.User;
import com.dp.rosseti.ui.adapters.TopUsersListAdapter;

import java.util.List;

public class TopUsersFragment extends Fragment {

    private TopUsersViewModel mTopUsersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mTopUsersViewModel =
                new ViewModelProvider(this).get(TopUsersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_topusers, container, false);
        /*final TextView textView = root.findViewById(R.id.text_topusers);
        topUsersViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        RecyclerView recyclerView = root.findViewById(R.id.topuser_recyclerview);
        final TopUsersListAdapter adapter = new TopUsersListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mTopUsersViewModel.getAllTopUsers().observe(getActivity(), new Observer<List<TopUser>>() {
            @Override
            public void onChanged(@Nullable final List<TopUser> topUsers) {
                // Update the cached copy of the words in the adapter.
                adapter.setTopUsers(topUsers);
            }
        });

        return root;
    }
}