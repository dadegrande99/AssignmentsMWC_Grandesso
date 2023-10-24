package com.example.stepappv2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.stepappv2.R;
import com.example.stepappv2.databinding.FragmentHomeBinding;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private int counter;
    private int goal;
    private TextView stepCountsView;
    private TextView goalView;
    private CircularProgressIndicator progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        counter = 0;
        goal = 50;

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        goalView = (TextView) root.findViewById(R.id.goal);
        goalView.setText("Goal: " + Integer.toString(goal));

        stepCountsView = (TextView) root.findViewById(R.id.counter);
        stepCountsView.setText(Integer.toString(counter));

        Button count = (Button) root.findViewById(R.id.button_count);
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                stepCountsView.setText(Integer.toString(counter));
                progressBar.setProgress(counter);
            }
        });
        Button start = (Button) root.findViewById(R.id.start_button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0;
                stepCountsView.setText(Integer.toString(counter));
                progressBar.setProgress(counter);
            }
        });

        progressBar = (CircularProgressIndicator) root.findViewById(R.id.progressBar);
        progressBar.setMax(goal);
        progressBar.setProgress(counter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}