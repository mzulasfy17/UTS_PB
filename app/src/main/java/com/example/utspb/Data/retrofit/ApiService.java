package com.example.utspb.Data.retrofit;

import com.example.utspb.Data.response.GitHubSearchResponse;
import com.example.utspb.Data.response.GithubUsers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
//    @Headers({"Authorization: token <MY_TOKEN>"})
    @GET("search/users")
    Call<GitHubSearchResponse> searchUsers(@Query("q") String query);

//     @Headers({"Authorization: token <MY_TOKEN>"})
    @GET("users/{username}")
    Call<GithubUsers> getUser(@Path("username") String username);


}
