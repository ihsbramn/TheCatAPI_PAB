package com.rab.thecatapi_pab.ui.fav;

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

import com.google.gson.Gson;
import com.rab.thecatapi_pab.databinding.FragmentFavBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FavFragment extends Fragment {


    private static final String TAG = "FavFragment";
    private FavViewModel favViewModel;
    String BASE_URL = "https://api.thecatapi.com/v1/";
    Handler uiHandler = new Handler(Looper.getMainLooper());
    OkHttpClient client = new OkHttpClient();
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

        Request favReq = new Request.Builder()
                .url(BASE_URL+"favourites")
                .addHeader("x-api-key","b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                .build();

        client.newCall(favReq).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                        debug logger
                Log.d(TAG, "onFailure: ERROR!");
                e.printStackTrace();
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                        debug logger
                Log.d(TAG, "onResponse: Success"+ response);
                if (response.isSuccessful()) {
                    String jsonResponse = null;
                    try {
                        jsonResponse = response.body().string();

                        JSONArray arrayJsonResp = new JSONArray(jsonResponse);
                        JSONObject objectJsonResp = arrayJsonResp.getJSONObject(0);
                        String id = objectJsonResp.getString("id");
//                        imgFavIDList.clear();


//                        String dataid = imgFavIDList.toString();

                        Log.d(TAG, "data array id img " + id);

                        Log.d(TAG, "onResponse: " + arrayJsonResp);
                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    binding.FavData.setText(arrayJsonResp.toString(4));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    } catch (IOException | JSONException e) {
//                                debug logger
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