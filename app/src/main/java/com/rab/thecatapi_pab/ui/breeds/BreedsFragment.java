package com.rab.thecatapi_pab.ui.breeds;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BreedsFragment<root> extends Fragment {

    private BreedsViewModel breedsViewModel;
    private FragmentBreedsBinding binding;
    String BASE_URL = "https://api.thecatapi.com/v1/";
    private static final String TAG = "BreedsFragment";
    Handler uiHandler = new Handler(Looper.getMainLooper());
    OkHttpClient client = new OkHttpClient();

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

        Request breedsReq = new Request.Builder()
                .url(BASE_URL + "breeds")
                .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                .build();

        client.newCall(breedsReq).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure: Failed");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Success" + response);
                    String jsonResponse = null;
                    try {
                        jsonResponse = response.body().string();
                        JSONArray arrayJsonResp = new JSONArray(jsonResponse);

                        Log.d(TAG, "onResponse: Data : " + arrayJsonResp);

                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    binding.breedsData.setText(arrayJsonResp.toString(4));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
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