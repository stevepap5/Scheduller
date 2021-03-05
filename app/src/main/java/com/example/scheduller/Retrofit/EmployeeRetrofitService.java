package com.example.scheduller.Retrofit;

import com.example.scheduller.Api.EmployeeApi;
import com.example.scheduller.Api.SkillsApi;
import com.example.scheduller.Employees.Employee;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeRetrofitService {

    private static final String BASE_URL = "http://192.168.1.6/";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static EmployeeApi getInterface() {
        return retrofit.create(EmployeeApi.class);
    }
}
