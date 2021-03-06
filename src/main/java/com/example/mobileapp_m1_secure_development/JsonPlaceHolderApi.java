package com.example.mobileapp_m1_secure_development;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("accounts")
    Call<List<Account>> getPosts();

    @POST("accounts")
    Call<Account> createPost (@Body Account account);
}
