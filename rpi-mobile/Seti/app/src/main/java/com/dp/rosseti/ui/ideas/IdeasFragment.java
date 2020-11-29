package com.dp.rosseti.ui.ideas;

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

public class IdeasFragment extends Fragment {

    private IdeasViewModel ideasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ideasViewModel =
                new ViewModelProvider(this).get(IdeasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ideas, container, false);
        final TextView textView = root.findViewById(R.id.tv_ideas);
        ideasViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        /*SearchView searchView = (SearchView) root.findViewById(R.id.searchView);
        ImageView icon = searchView.findViewById(R.id.search_button);
        icon.setColorFilter(Color.WHITE);*/

        return root;
    }
}