package com.rab.thecatapi_pab.ui.breeds;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.rab.thecatapi_pab.R;
import com.rab.thecatapi_pab.databinding.FragmentBreedsBinding;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    List<String> breedsname = new ArrayList<String>();
    List<String> breedsdesc = new ArrayList<String>();
    List<String> breedsurl = new ArrayList<String>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_breeds, container, false);
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
                        for (int i = 0; i < arrayJsonResp.length(); i++) {
                            JSONObject breedsObject = arrayJsonResp.getJSONObject(i);
                            String BreedsName = breedsObject.getString("name");
                            String BreedsDesc = breedsObject.getString("description");
//                            String ImageID = breedsObject.getString("reference_image_id");
//                            String url = breedsObject.getJSONObject("image").getString("url");
                            breedsname.add(BreedsName);
                            breedsdesc.add(BreedsDesc);
//                            breedsurl.add(BreedsUrl);
//                            Log.d(TAG, "Breeds List : " + breedsname);
//                            Log.d(TAG, "Breeds Url List : " + breedsdesc);


                        }
//                        Log.d(TAG, "onResponse: Data : " + arrayJsonResp);
                        Spinner spinner = binding.spinner;
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                ImageView imageView = binding.ImgBreeds;
                                Object item = adapterView.getItemAtPosition(position);
                                switch (position) {
                                    case 0:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(0)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(0)));
//                                                    Picasso
//                                                            .get()
//                                                            .load(String.valueOf(breedsurl.get(0)))
//                                                            .into(imageView);

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 1:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(1)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(1)));

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 2:
                                        Log.d(TAG, "onItemSelected: Aegan");
                                        break;
                                    case 3:
                                        Log.d(TAG, "onItemSelected: Aegan");
                                        break;
                                    case 4:
                                        Log.d(TAG, "onItemSelected: Aegan");
                                        break;
                                    case 5:
                                        Log.d(TAG, "onItemSelected: Aegan");
                                        break;
                                    case 6:
                                        Log.d(TAG, "onItemSelected: Aegan");
                                        break;
                                    case 7:
                                        Log.d(TAG, "onItemSelected: Aegan");
                                        break;
                                    case 8:
                                        Log.d(TAG, "onItemSelected: Aegan");
                                        break;
                                    case 9:
                                        Log.d(TAG, "onItemSelected: Aegan");
                                        break;
                                }
                                Log.d(TAG, "onItemSelected: " + item);

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });


//                        uiHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    binding.breedsData.setText(arrayJsonResp.toString(4));
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return root;
    }


//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}