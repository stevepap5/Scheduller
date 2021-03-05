package com.example.scheduller.CreateNewSkillActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scheduller.R;
import com.example.scheduller.SkillsActivity.Skills;
import com.example.scheduller.SkillsActivity.SkillsActivity;
import com.example.scheduller.ViewModels.SkillsActivityViewmodel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateNewSkillActivity extends AppCompatActivity {

    private TextInputLayout createNewNameSkillTextInputLayout;
    private TextInputLayout createNewDescriptionSkillTextInputLayout;
    private EditText createNewNameSkillEditText;
    private EditText createNewDescriptionEditText;
    private MaterialButton saveNewSkillMaterialButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_skill);

        setFindViewById();
        createNewSkill();


    }

    private void setFindViewById(){

        createNewNameSkillTextInputLayout=findViewById(R.id.newSkillsNameTextInputLayoutXml);
        createNewDescriptionSkillTextInputLayout=findViewById(R.id.newSkillsDescriptionMaterialTextInputLayoutXml);
        createNewNameSkillEditText=findViewById(R.id.newSkillEditTextXml);
        createNewDescriptionEditText=findViewById(R.id.newSkillDescriptionEditTextXml);
        saveNewSkillMaterialButton=findViewById(R.id.saveNewSkillButtonXml);

    }


    private void createNewSkill(){

       saveNewSkillMaterialButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(!createNewNameSkillEditText.getText().toString().trim().isEmpty()&&
                       !createNewDescriptionEditText.getText().toString().trim().isEmpty()){

                   Skills newSkill=new Skills();
                   newSkill.setSkillName(createNewNameSkillEditText.getText().toString().trim());
                   newSkill.setSkillDescription(createNewDescriptionEditText.getText().toString().trim());

                   Date c = Calendar.getInstance().getTime();

                   SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                   String skillsDate = df.format(c);

                   newSkill.setDateOfCreation(skillsDate);

                   SkillsActivityViewmodel createSkillsViewModel=new ViewModelProvider(CreateNewSkillActivity.this).get(SkillsActivityViewmodel.class);
                   createSkillsViewModel.createSkills(newSkill);
                   Toast.makeText(CreateNewSkillActivity.this,"New skill created",Toast.LENGTH_LONG).show();
                   Intent intent=new Intent(CreateNewSkillActivity.this, SkillsActivity.class);
                   startActivity(intent);
               }
           }
       });}
}