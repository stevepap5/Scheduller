package com.example.scheduller.SkillsActivity;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


public class Skills {


    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String skillName;

    private String skillDescription;

    private String dateOfCreation;

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }
}
