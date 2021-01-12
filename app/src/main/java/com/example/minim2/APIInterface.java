package com.example.minim2;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("/users/{username}")
    Call<GithubUserClass> getUser(@Path("username") String username);

    @GET("/users/{username}/repos")
    Call<ArrayList<GithubRepoClass>> getRepos(@Path("username") String username);

}
