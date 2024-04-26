package com.example.utspb.Data.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GitHubSearchResponse {

    @SerializedName("items")
    private List<GithubUsers> users;

    public List<GithubUsers> getUsers() {

        return users;
    }
}
