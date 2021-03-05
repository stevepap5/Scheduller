package com.example.scheduller.Repository;

import android.util.Base64;

import androidx.lifecycle.MutableLiveData;

import com.example.scheduller.Api.SkillsApi;
import com.example.scheduller.Retrofit.SkillsRetrofitService;
import com.example.scheduller.SkillsActivity.Skills;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillsRepository {

    private final MutableLiveData<List<Skills>> listOfSkills = new MutableLiveData<>();
    private String username = "stefanos";
    private String password = "11";
    private String base = username + ":" + password;
    private String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
    private static SkillsApi skillsApi;
    private final MutableLiveData<Skills> skillsMutableLiveData=new MutableLiveData<>();

    private static SkillsRepository skillsRepository;

    public static SkillsRepository getInstance() {
        if (skillsRepository == null) {
            skillsRepository = new SkillsRepository();
        }
        return skillsRepository;
    }

    public SkillsRepository() {
        skillsApi = SkillsRetrofitService.getInterface();
    }

    public MutableLiveData<List<Skills>> getSkillsList() {

        Call<List<Skills>> call = skillsApi.getSkillsList(authHeader);
        call.enqueue(new Callback<List<Skills>>() {
            @Override
            public void onResponse(Call<List<Skills>> call, Response<List<Skills>> response) {

                listOfSkills.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<Skills>> call, Throwable t) {

            }
        });


        return listOfSkills;
    }

    public MutableLiveData<Skills> getSkills(String skillsId) {


        Call<Skills> call = skillsApi.getSkills(authHeader,skillsId);
        call.enqueue(new Callback<Skills>() {
            @Override
            public void onResponse(Call<Skills> call, Response<Skills> response) {

                skillsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Skills> call, Throwable t) {

                skillsMutableLiveData.postValue(null);
            }
        });
        return skillsMutableLiveData;
    }

    public MutableLiveData<Skills> updateSkills(String skillsId,Skills skills) {


        Call<Skills> call = skillsApi.updateSkills(authHeader,skillsId,skills);
        call.enqueue(new Callback<Skills>() {
            @Override
            public void onResponse(Call<Skills> call, Response<Skills> response) {

                skillsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Skills> call, Throwable t) {

                skillsMutableLiveData.postValue(null);
            }
        });
        return skillsMutableLiveData;
    }

    public void deleteSkills(String skillsId) {


        Call<Skills> call = skillsApi.deleteSkills(authHeader,skillsId);
        call.enqueue(new Callback<Skills>() {
            @Override
            public void onResponse(Call<Skills> call, Response<Skills> response) {

            }

            @Override
            public void onFailure(Call<Skills> call, Throwable t) {

            }
        });

    }

    public MutableLiveData<Skills> createSkills(Skills skills) {


        Call<Skills> call = skillsApi.createSkills(authHeader,skills);
        call.enqueue(new Callback<Skills>() {
            @Override
            public void onResponse(Call<Skills> call, Response<Skills> response) {

                skillsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Skills> call, Throwable t) {

                skillsMutableLiveData.postValue(null);
            }
        });
        return skillsMutableLiveData;
    }

}
