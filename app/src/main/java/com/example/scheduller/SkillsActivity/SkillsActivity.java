package com.example.scheduller.SkillsActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.scheduller.CreateNewSkillActivity.CreateNewSkillActivity;
import com.example.scheduller.ParticularSkillActivity.ParticularSkillActivity;
import com.example.scheduller.R;
import com.example.scheduller.ViewModels.SkillsActivityViewmodel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SkillsActivity extends AppCompatActivity  {


    private RecyclerView skillRecyclerView;
    private SkillsRecyclerViewAdapter skillsRecyclerViewAdapter;
    private List<Skills> listOfSkills=new ArrayList<>();
    private FloatingActionButton createNewSkillFloatingButton;
    private Toolbar skillsToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

            setSkillToolBar();
            setSkillsRecyclerView();
            goToCreateNewSkillActivityMethod();


    }

    private void goToCreateNewSkillActivityMethod(){

        createNewSkillFloatingButton=findViewById(R.id.createNewSkillFloatingButtonXml);
        createNewSkillFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SkillsActivity.this, CreateNewSkillActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setSkillToolBar(){

        skillsToolBar=findViewById(R.id.skillToolbarXml);
        setSupportActionBar(skillsToolBar);
        getSupportActionBar().setTitle(R.string.skills_string);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setSkillsRecyclerView() {

        skillRecyclerView = findViewById(R.id.skillRecyclerviewXml);
        skillRecyclerView.setHasFixedSize(true);
        skillRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        SkillsActivityViewmodel skillsActivityViewmodel= new ViewModelProvider(this).get(SkillsActivityViewmodel.class);
        skillsActivityViewmodel.getSkillsList().observe(this,new Observer<List<Skills>>() {
            @Override
            public void onChanged(List<Skills> skillsList) {
                if (skillsList!=null) {
                    listOfSkills.addAll(skillsList);
                    skillsRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });

        skillsRecyclerViewAdapter=new SkillsRecyclerViewAdapter(listOfSkills,this);
        skillRecyclerView.setAdapter(skillsRecyclerViewAdapter);


    }



}