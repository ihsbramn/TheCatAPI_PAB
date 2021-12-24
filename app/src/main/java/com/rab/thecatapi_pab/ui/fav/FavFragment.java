package com.rab.thecatapi_pab.ui.fav;

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

import com.rab.thecatapi_pab.databinding.FragmentFavBinding;

public class FavFragment extends Fragment {

    private FavViewModel favViewModel;
    private FragmentFavBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favViewModel =
                new ViewModelProvider(this).get(FavViewModel.class);

        binding = FragmentFavBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFavourites;
        favViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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