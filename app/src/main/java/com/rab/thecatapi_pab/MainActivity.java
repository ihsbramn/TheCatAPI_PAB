package com.rab.thecatapi_pab;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.rab.thecatapi_pab.databinding.ActivityMainBinding;
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

public class MainActivity extends AppCompatActivity {

    private static final String TAG1 = "HomeFragment";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_breeds, R.id.navigation_search, R.id.navigation_favourites)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Home Act

        //        click random cat
        findViewById(R.id.RandomCatCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//        okhttp client
                OkHttpClient client = new OkHttpClient();
//        url
                String BASE_URL = "https://api.thecatapi.com/v1/";
                Request randomCat = new Request.Builder()
                        .url(BASE_URL + "images/search")
                        .addHeader("x-api-key","b7dfc536-2e87-40ac-ba6f-d3f14154ebee")
                        .build();

                client.newCall(randomCat).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Log.d(TAG1, "onFailure: ERROR!");
                        e.printStackTrace();
                    }
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Log.d(TAG1, "onResponse: Got Response");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (response.isSuccessful()){
                                    String json = null;
                                    try {
                                        json = response.body().string();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
//                                    get string from json string
                                    try {
                                        JSONArray arrayJSON_RESP = new JSONArray(json);
                                        JSONObject objectJSON_RESP = arrayJSON_RESP.getJSONObject(0);
                                        String url = objectJSON_RESP.getString("url");
////                                        set text
//                                        TextView textView = findViewById(R.id.RandomCatTextJSON);
//                                        textView.setText(url);
//                                        set img
                                        ImageView imageView = findViewById(R.id.imageContainer);
                                        Picasso.get().load(url).into(imageView);
//                                        debug logger
                                        Log.d(TAG1, "onResponse: text and img set on "+url);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }
                        });

                    }
                });
            }
        });
    }

}