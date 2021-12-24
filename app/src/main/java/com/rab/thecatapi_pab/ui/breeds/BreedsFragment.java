package com.rab.thecatapi_pab.ui.breeds;

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

import com.rab.thecatapi_pab.databinding.FragmentBreedsBinding;
import com.rab.thecatapi_pab.databinding.FragmentBreedsBinding;

public class BreedsFragment extends Fragment {

    private BreedsViewModel breedsViewModel;
    private FragmentBreedsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        breedsViewModel =
                new ViewModelProvider(this).get(BreedsViewModel.class);

        binding = FragmentBreedsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textBreeds;
        breedsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}