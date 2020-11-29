package com.dp.rosseti.ui.ideabox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dp.rosseti.R;
import com.dp.rosseti.data.db.entities.ShortIdea;
import com.dp.rosseti.ui.adapters.ShortIdeasListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class IdeaboxFragment extends Fragment {

    private IdeaboxViewModel ideaboxViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ideaboxViewModel =
                new ViewModelProvider(this).get(IdeaboxViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ideabox, container, false);
        final TextView textView = root.findViewById(R.id.text_challenges);

        FloatingActionButton fab = root.findViewById(R.id.fab_add_idea);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Soon you can add your idea", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.shortideas_recyclerview);
        final ShortIdeasListAdapter adapter = new ShortIdeasListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ideaboxViewModel.getAllShortIdeas().observe(getActivity(), new Observer<List<ShortIdea>>() {
            @Override
            public void onChanged(@Nullable final List<ShortIdea> shortIdeas) {
                // Update the cached copy of the words in the adapter.
                adapter.setShortIdea(shortIdeas);
            }
        });

        /*SearchView searchView = (SearchView) root.findViewById(R.id.searchView);
        ImageView icon = searchView.findViewById(R.id.search_button);
        icon.setColorFilter(Color.WHITE);*/

        return root;
    }
}