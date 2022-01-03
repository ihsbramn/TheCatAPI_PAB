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

import com.google.gson.Gson;
import com.rab.thecatapi_pab.R;
import com.rab.thecatapi_pab.databinding.FragmentBreedsBinding;
import com.squareup.picasso.Picasso;


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
                            breedsname.add(BreedsName);
                            breedsdesc.add(BreedsDesc);
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
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg")
                                                            .into(imageView);

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
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/ozEvzdVM-.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 2:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(2)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(2)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/hBXicehMA.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 3:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(3)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(3)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/xnsqonbjW.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 4:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(4)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(4)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/JFPROfGtQ.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 5:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(5)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(5)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/8D--jCd21.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 6:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(6)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(6)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/k71ULYfRr.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 7:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(7)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(7)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/_6x-3TiCA.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 8:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(8)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(8)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/13MkvUreZ.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 9:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(9)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(9)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/5AdhMjeEu.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 10:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(10)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(10)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/O3btzLlsO.png")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 11:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(11)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(11)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/HOrX5gwLS.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 12:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(12)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(12)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/5iYq9NmT1.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 13:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(13)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(13)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/7isAO4Cav.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 14:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(14)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(14)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/s4wQfYoEk.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 15:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(15)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(15)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/4lXnnfxac.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 16:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(16)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(16)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/jvg3XfEdC.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 17:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(17)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(17)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/B1ERTmgph.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 18:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(18)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(18)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/TR-5nAd_S.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 19:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(19)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(19)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/j6oFGLpRG.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 20:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(20)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(20)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/vJ3lEYgXr.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 21:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(21)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(21)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/IFXsxmXLm.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 22:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(22)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(22)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/oSpqGyUDS.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 23:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(23)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(23)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/unX21IBVB.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 24:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(24)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(24)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/3dbtapCWM.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 25:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(25)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(25)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/tJbzb7FKo.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 26:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(26)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(26)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/4RzEwvyzz.png")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 27:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(27)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(27)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/3KG57GfMW.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 28:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(28)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(28)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/BQMSld0A0.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 29:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(29)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(29)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/TuSyTkt2n.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 30:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(20)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(20)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/TuSyTkt2n.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 31:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(31)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(31)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/YnPrYEmfe.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 32:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(32)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(32)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/njK25knLH.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 33:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(33)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(33)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/CDhOtM-Ig.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 34:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(34)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(34)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/-tm9-znzl.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 35:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(35)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(35)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/xoI_EpOKe.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 36:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(36)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(36)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/165ok6ESN.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 37:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(37)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(37)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/DbwiefiaY.png")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 38:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(38)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(38)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/NZpO4pU56M.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 39:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(39)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(39)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/aKbsEYjSl.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 40:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(40)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(40)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/OOD3VXAQn.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 41:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(41)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(41)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/OOD3VXAQn.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 42:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(42)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(42)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/fhYh2PDcC.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 43:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(43)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(43)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/j5cVSqLer.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 44:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(44)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(44)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/OGTWqNNOt.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 45:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(45)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(45)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/06dgGmEOV.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 46:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(46)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(46)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/JAx-08Y0n.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 47:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(47)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(47)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/LutjkZJpH.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 48:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(48)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(48)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/LutjkZJpH.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 49:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(49)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(49)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/z7fJRNeN6.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 50:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(50)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(50)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/SMuZx-bFM.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 51:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(51)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(51)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/oGefY4YoG.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 52:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(52)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(52)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/Rhj-JsTLP.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 53:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(53)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(53)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/a8nIYvs6S.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 54:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(54)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(54)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/o9t0LDcsa.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 55:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(55)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(55)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/II9dOZmrw.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 56:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(56)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(56)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/ai6Jps4sx.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 57:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(57)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(57)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/3bkZAjRh1.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 58:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(58)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(58)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/Qtncp2nRe.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 59:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(59)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(59)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/MK-sYESvO.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 60:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(20)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(20)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/EPF2ejNS0.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 61:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(61)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(61)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/BDb8ZXb1v.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 62:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(62)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(62)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/KBroiVNCM.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 63:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(63)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(63)));

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 64:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(64)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(64)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/7CGV6WVXq.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 65:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(65)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(65)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/sxIXJax6h.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 66:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(66)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(66)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/sxIXJax6h.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 67:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(67)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(67)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/sxIXJax6h.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                    case 68:
                                        uiHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    binding.TxtBreeds.setText(String.valueOf(breedsname.get(68)));
                                                    binding.DescBreeds.setText(String.valueOf(breedsdesc.get(68)));
                                                    Picasso
                                                            .get()
                                                            .load("https://cdn2.thecatapi.com/images/0SxW2SQ_S.jpg")
                                                            .into(imageView);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
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