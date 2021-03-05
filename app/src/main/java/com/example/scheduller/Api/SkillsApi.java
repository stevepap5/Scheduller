package com.example.scheduller.Api;


import com.example.scheduller.SkillsActivity.Skills;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SkillsApi {

    @GET("/skills")
    Call<List<Skills>> getSkillsList(@Header("Authorization") String authHeader);

    @GET("/skills/{id}")
    Call<Skills> getSkills(@Header("Authorization") String authHeader,@Path("id") String id);

    @POST("/skills")
    Call<Skills> createSkills(@Header("Authorization") String authHeader,@Body Skills skills);

    @PUT("/skills/{id}")
    Call<Skills> updateSkills(@Header("Authorization") String authHeader,@Path("id") String id,@Body Skills skills);

    @DELETE("/skills/{id}")
    Call<Skills> deleteSkills(@Header("Authorization") String authHeader,@Path("id") String id);
}
