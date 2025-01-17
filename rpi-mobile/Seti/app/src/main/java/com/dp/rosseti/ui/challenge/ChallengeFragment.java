package com.dp.rosseti.ui.challenge;

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

public class ChallengeFragment extends Fragment {

    private ChallengeViewModel challengeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        challengeViewModel =
                new ViewModelProvider(this).get(ChallengeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_challenges, container, false);
        final TextView textView = root.findViewById(R.id.text_challenges);
        challengeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}