package com.example.utspb.Data.response;

import com.google.gson.annotations.SerializedName;

public class GithubUsers {
    @SerializedName("login")
    private String username;
    @SerializedName("avatar_url")
    private String avatarUrl;
    private String name;
    private String bio;
    private String followers;
    private String following;
    private String location;
    private String company;

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    public String getLocate() {
        return location;
    }

    public String getCompany() {
        return company;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

}
