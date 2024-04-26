package com.example.utspb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.utspb.Data.response.GitHubSearchResponse;
import com.example.utspb.Data.response.GithubUsers;
import com.example.utspb.Data.retrofit.ApiConfig;
import com.example.utspb.Data.retrofit.ApiService;
import com.example.utspb.ui.ReviewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        ApiService apiService = ApiConfig.getApiService();
        Call<GitHubSearchResponse> call = apiService.searchUsers("Zul");

        call.enqueue(new Callback<GitHubSearchResponse>() {
            @Override
            public void onResponse(Call<GitHubSearchResponse> call, Response<GitHubSearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<GithubUsers> users = response.body().getUsers();
                    adapter = new ReviewAdapter(users);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } else {
                    Toast.makeText(MainActivity.this, "Failed to get users", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GitHubSearchResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}