package com.example.scheduller.Retrofit;

import android.util.Base64;

import com.example.scheduller.Api.SkillsApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkillsRetrofitService {

    private static final String BASE_URL = "http://192.168.1.6/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static SkillsApi getInterface() {
        return retrofit.create(SkillsApi.class);
    }
}
