package com.dp.rosseti.ui.ideabox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dp.rosseti.R;
import com.dp.rosseti.data.db.RossetiDatabase;
import com.dp.rosseti.data.db.daos.ShortIdeaDao;
import com.dp.rosseti.data.db.entities.ShortIdea;
import com.dp.rosseti.data.repos.ShortIdeasRepository;
import com.dp.rosseti.ui.adapters.ShortIdeasListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Random;

public class NewIdeaFragment extends Fragment {

    private NewIdeaViewModel newIdeaViewModel;
    private EditText titleEditText;
    private EditText shortDescriptionEditText;
    private TextInputLayout detailDescriptionInputLayout;
    private Button attachmentButton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newIdeaViewModel =
                new ViewModelProvider(this).get(NewIdeaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_newidea, container, false);

        titleEditText = root.findViewById(R.id.et_newidea_title);
        shortDescriptionEditText = root.findViewById(R.id.et_newidea_shortdescription);
        detailDescriptionInputLayout = root.findViewById(R.id.ti_detaildescription);
        attachmentButton = root.findViewById(R.id.bt_newidea_attachment);

        Button save_button = root.findViewById(R.id.bt_newidea_save);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEditText.getText().toString();
                String shortDescription = shortDescriptionEditText.getText().toString();
                String detailDescription = detailDescriptionInputLayout.getEditText().getText().toString();
                ShortIdeasRepository mRepository = new ShortIdeasRepository(getActivity().getApplication());
                Random random = new Random();
                int id = random.nextInt(1000);
                mRepository.insert(new ShortIdea(id, title,"Alexander Smirnov", shortDescription,"none","NEW"));
                Navigation.findNavController(view).navigate(R.id.action_nav_newidea_to_nav_ideabox);
            }
        });

        attachmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return root;
    }
}