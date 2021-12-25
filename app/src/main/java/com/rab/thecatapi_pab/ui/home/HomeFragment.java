package com.rab.thecatapi_pab.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.rab.thecatapi_pab.R;
import com.rab.thecatapi_pab.databinding.FragmentHomeBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        binding text
        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

//        binding random cat call
        binding.RandomCatCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                OkHttpClient
                OkHttpClient client = new OkHttpClient();
//                endpoint
                String BASE_URL = "https://api.thecatapi.com/v1/";
                Request randomCat = new Request.Builder()
                        .url(BASE_URL + "images/search")
                        .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                        .build();

                client.newCall(randomCat).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                        debug logger
                        Log.d(TAG, "onFailure: ERROR!");
                        e.printStackTrace();
                    }
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                        debug logger
                        Log.d(TAG, "onResponse: Got Response");
                        if (response.isSuccessful()) {
                            String json = null;
                            try {
                                json = response.body().string();
                            } catch (IOException e) {
//                                debug logger
                                e.printStackTrace();
                            }
                            try {
//                                get url string
                                JSONArray arrayJSON_RESP = new JSONArray(json);
                                JSONObject objectJSON_RESP = arrayJSON_RESP.getJSONObject(0);
                                String url = objectJSON_RESP.getString("url");

//                                Toast Property
                                Context context = getContext();
                                int duration = Toast.LENGTH_SHORT;
                                String text = ("Meow ! "+url);

//                                img binding
                                ImageView imageView = binding.imageContainer;

//                                UiHandler
                                Handler uiHandler = new Handler(Looper.getMainLooper());
                                uiHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Picasso
                                                .get()
                                                .load(url)
                                                .into(imageView);

                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                    }
                                });

                            } catch (JSONException e) {
//                                debug logger
                                e.printStackTrace();
                            }
                        }
                    }
                });
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