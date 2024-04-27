package com.example.utspb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utspb.Data.response.GithubUsers;
import com.example.utspb.Data.retrofit.ApiConfig;
import com.example.utspb.Data.retrofit.ApiService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.progressBar);

        btnShare = findViewById(R.id.btnshare);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareUserData();
            }
        });


        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String username = extras.getString("username");
            ApiService apiService = ApiConfig.getApiService();
            Call<GithubUsers> userCall = apiService.getUser(username);

            TextView textView = findViewById(R.id.nama);
            TextView textView2 = findViewById(R.id.username);
            TextView textView3 = findViewById(R.id.bio);
            TextView textView4 = findViewById(R.id.follower);
            TextView textView5 = findViewById(R.id.following);
            TextView textView6 = findViewById(R.id.locate);
            TextView textView7 = findViewById(R.id.company);
            ImageView imageView = findViewById(R.id.foto);

            showLoading(true);
            userCall.enqueue(new Callback<GithubUsers>() {
                @Override
                public void onResponse(Call<GithubUsers> call, Response<GithubUsers> response) {
                    if (response.isSuccessful()){
                        showLoading(false);
                        GithubUsers user = response.body();
                        if (user != null){
                            String name = "Name: " + user.getName();
                            String usernames = user.getUsername();
                            String bio = "Bio: " + user.getBio();
                            String foto = user.getAvatarUrl();
                            String followers = user.getFollowers();
                            String following = user.getFollowing();
                            String locate = "locate : " + user.getLocate();
                            String company = "Company : " + user.getCompany();

                            textView.setText(name);
                            textView2.setText(usernames);
                            textView3.setText(bio);
                            textView4.setText(followers);
                            textView5.setText(following);
                            textView6.setText(locate);
                            textView7.setText(company);
                            Picasso.get().load(foto).into(imageView);
                        }
                    }
                }

                @Override
                public void onFailure(Call<GithubUsers> call, Throwable t) {
                    Toast.makeText(Detail.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void shareUserData() {
        String name = getIntent().getStringExtra("name");
        String username = getIntent().getStringExtra("username");
        String bio = getIntent().getStringExtra("bio");
        String locate = getIntent().getStringExtra("locate");
        String company = getIntent().getStringExtra("company");
        String foto = getIntent().getStringExtra("foto");

        String shareText = "Name: " + name + "\nUsername: " + username + "\nBio: " + bio + "\nLocation: " + locate + "\nCompany: " + company;

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);

        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        } else {
            Toast.makeText(this, "No app available to handle this action", Toast.LENGTH_SHORT).show();
        }
    }
}