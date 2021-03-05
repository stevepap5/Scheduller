package com.example.scheduller.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.scheduller.Repository.SkillsRepository;
import com.example.scheduller.SkillsActivity.Skills;


import java.util.List;

public class SkillsActivityViewmodel extends AndroidViewModel {

    private final SkillsRepository skillsRepository;
    public SkillsActivityViewmodel(@NonNull Application application) {
        super(application);

        skillsRepository = new SkillsRepository();
    }

    public MutableLiveData<List<Skills>> getSkillsList(){
      return  skillsRepository.getSkillsList();
    }

    public MutableLiveData<Skills> getSkills(String skillsId){
        return  skillsRepository.getSkills(skillsId);
    }

    public MutableLiveData<Skills> updateSkills(String skillsId,Skills skills){
        return  skillsRepository.updateSkills(skillsId,skills);
    }

    public void deleteSkills(String skillsId){
        skillsRepository.deleteSkills(skillsId);
    }

    public void createSkills(Skills skills){
        skillsRepository.createSkills(skills);
    }

}
