package com.dp.rosseti.ui.brainstorm;

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

import com.dp.rosseti.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class BrainstormFragment extends Fragment {

    private BrainstormViewModel brainstormViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        brainstormViewModel =
                new ViewModelProvider(this).get(BrainstormViewModel.class);
        View root = inflater.inflate(R.layout.fragment_brainstorm, container, false);
        final TextView textView = root.findViewById(R.id.text_brainstorm);
        brainstormViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        FloatingActionButton fab = root.findViewById(R.id.fab_add_brainstorm);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Soon you can add your brainstorm", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /*SearchView searchView = (SearchView) root.findViewById(R.id.searchView);
        ImageView icon = searchView.findViewById(R.id.search_button);
        icon.setColorFilter(Color.WHITE);*/

        return root;
    }
}