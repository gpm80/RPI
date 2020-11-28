package com.dp.rosseti.ui.users;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dp.rosseti.R;
import com.dp.rosseti.data.db.entities.User;
import com.dp.rosseti.ui.adapters.UsersListAdapter;

import java.util.List;

public class UsersFragment extends Fragment {

    private UsersViewModel mUsersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mUsersViewModel =
                new ViewModelProvider(this).get(UsersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_users, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerview);
        final UsersListAdapter adapter = new UsersListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mUsersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mUsersViewModel.getAllUsers().observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable final List<User> users) {
                // Update the cached copy of the words in the adapter.
                adapter.setUsers(users);
            }
        });



        return root;
    }
}