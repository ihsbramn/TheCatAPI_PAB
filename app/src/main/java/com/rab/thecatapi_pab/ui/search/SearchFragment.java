package com.rab.thecatapi_pab.ui.search;

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

import com.bumptech.glide.Glide;
import com.rab.thecatapi_pab.databinding.FragmentSearchBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SearchFragment extends Fragment {

    private SearchViewModel notificationsViewModel;
    private FragmentSearchBinding binding;
    OkHttpClient client = new OkHttpClient();
    private static final String TAG = "SearchFragment";
    String BASE_URL = "https://api.thecatapi.com/v1/";
    Handler uiHandler = new Handler(Looper.getMainLooper());
    String ImgURL = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSearch;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        Spinner spinFil = binding.spinnerFileType;
        Spinner spinBre = binding.spinnerBreeds;

        spinFil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("mime_types", "gif")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seFil = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();
                            client.newCall(seFil).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonResponseBody = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonResponseBody);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonResponseBody);
                                        JSONObject ObjectIMG = arrayJsonResp.getJSONObject(0);
                                        ImgURL = ObjectIMG.getString("url");

                                        Log.d(TAG, "onResponse: URL : " + ImgURL);
                                        ImageView imageView = binding.imageSearch;

                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                Glide.with(getContext())
                                                        .load(ImgURL)
                                                        .into(imageView);
                                            }
                                        });
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("mime_types", "jpg,png")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seFil = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();
                            client.newCall(seFil).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonResponseBody = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonResponseBody);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonResponseBody);
                                        JSONObject ObjectIMG = arrayJsonResp.getJSONObject(0);
                                        ImgURL = ObjectIMG.getString("url");

                                        Log.d(TAG, "onResponse: URL : " + ImgURL);
                                        ImageView imageView = binding.imageSearch;

                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                Picasso
                                                        .get()
                                                        .load(ImgURL)
                                                        .into(imageView);
                                            }
                                        });

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinBre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "abys")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "aege")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "abob")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "acur")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "asho")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 5:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "awir")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 6:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "amau")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 7:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "amis")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 8:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "bali")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 9:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "bamb")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 10:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "beng")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 12:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "birm")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 13:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "bomb")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 14:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "bslo")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 15:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "bsho")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 16:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "bure")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 17:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "buri")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 18:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "cspa")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 19:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "ctif")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 20:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "char")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 21:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "chau")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 22:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "chee")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 23:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "csho")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 24:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "crex")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 25:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "cymr")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 26:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "drex")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 27:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "dons")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 28:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "lihu")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 29:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "emau")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 30:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "ebur")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 31:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "esho")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 32:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "hbro")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 33:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "hima")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 35:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "jbob")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 36:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "java")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 37:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "khao")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 38:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "kora")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 39:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "kuri")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 40:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "lape")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 41:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "mcoo")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 42:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "mala")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 43:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "manx")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 44:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "munc")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 45:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "nebe")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 46:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "norw")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 47:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "ocic")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 48:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "orie")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 49:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "pers")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 50:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "pixi")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 51:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "raga")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 52:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "ragd")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 53:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "rblu")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 54:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "sava")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 55:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "sfol")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 56:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "srex")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 57:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "siam")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 58:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "sibe")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 59:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "sing")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 60:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "snow")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 61:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "soma")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 62:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "sphy")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 63:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "tonk")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 64:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "toyg")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 65:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "tang")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 66:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "tvan")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 67:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "ycho")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 68:
                        try {
                            HttpUrl url = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host("api.thecatapi.com")
                                    .addPathSegment("v1")
                                    .addPathSegment("images")
                                    .addPathSegment("search")
                                    .addQueryParameter("breed_ids", "ycho")
                                    .build();

                            Log.d(TAG, "onItemSelected: " + url);

                            Request seBreeds = new Request.Builder()
                                    .url(url)
                                    .addHeader("x-api-key", "b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                                    .build();

                            client.newCall(seBreeds).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Log.d(TAG, "onFailure: Failed");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    String jsonresponse = response.body().string();
                                    Log.d(TAG, "onResponse: " + response + jsonresponse);
                                    try {
                                        JSONArray arrayJsonResp = new JSONArray(jsonresponse);
                                        JSONObject Object = arrayJsonResp.getJSONObject(0);
                                        ImgURL = Object.getString("url");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                            Log.d(TAG, "onItemSelected: " + ImgURL);
                            ImageView imageView = binding.imageSearch;

                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso
                                            .get()
                                            .load(ImgURL)
                                            .into(imageView);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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