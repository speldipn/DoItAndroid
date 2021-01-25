package com.tpmn.doitandroid;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";
    public static final String API_KEY = "a3e02fe5f2d64c07865f25f3d94c0c21";

    RecyclerView recyclerView;
    Button getButton;

    Retrofit retrofit;
    APIService apiService;
    NewsApdater newsApdater;

    public interface APIService {
        @GET("everything")
        Call<NewsModel> getNews(@Query("q") String query, @Query("from") String from, @Query("sortBy") String sortBy, @Query("apiKey") String key);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        initAPI();
    }

    private void initAPI() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://newsapi.org/v2/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        apiService = retrofit.create(APIService.class);
    }

    private void setup() {
        recyclerView = findViewById(R.id.recyclerView);
        getButton = findViewById(R.id.getButton);

        getButton.setOnClickListener(v -> {
            request();
        });

        newsApdater = new NewsApdater();
        recyclerView.setAdapter(newsApdater);
    }

    private void request() {
        String query = "game";
        String from = "2021-01-25";
        String sortBy = "publishedAt";
        String apiKey = API_KEY;

        apiService.getNews(query, from, sortBy, apiKey).enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if(response.isSuccessful()) {
                    newsApdater.setDataAndRefresh(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {

            }
        });
    }
}