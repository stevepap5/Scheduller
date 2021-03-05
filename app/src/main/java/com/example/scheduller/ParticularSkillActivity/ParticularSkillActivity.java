package com.example.scheduller.ParticularSkillActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.scheduller.ParticularEmployeeActivity.ParticularEmployeeActivity;
import com.example.scheduller.R;
import com.example.scheduller.SkillsActivity.Skills;
import com.example.scheduller.SkillsActivity.SkillsActivity;
import com.example.scheduller.ViewModels.SkillsActivityViewmodel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

public class ParticularSkillActivity extends AppCompatActivity {

    private TextInputLayout skillsNameTextInputLayout;
    private TextInputLayout skillsDescriptionTextInputLayout;
    private TextInputLayout skillsDateTextInputLayout;
    private MaterialTextView skillNameMaterialTextView;
    private MaterialTextView skillDescriptionMaterialTextView;
    private MaterialTextView skillDateMaterialTextView;
    private Toolbar particularSkillToolbar;
    private MaterialButton skillUpdateButton;
    private MaterialButton skillDeleteButton;
    private AlertDialog updateDialog;
    private AlertDialog.Builder updateDialogBuilder;
    private TextInputEditText updateEditText;
    private MaterialButton updateSkillDialogMaterialButton;
    private MaterialButton cancelUpdatedSkillMaterialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_skill);

        storeIntentExtras();
        setParticularSkillToolBar();
        setFindViewsById();
        getDataToFillSkills();
        setUpdateButton();
        setSkillDeleteButton();
    }

    private void setParticularSkillToolBar() {

        String skillsName = getIntent().getStringExtra("NAME");
        particularSkillToolbar = findViewById(R.id.particularSkillToolbarXml);
        setSupportActionBar(particularSkillToolbar);
        getSupportActionBar().setTitle(skillsName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getDataToFillSkills() {

        String skillsName = getIntent().getStringExtra("NAME");
        String skillsDescription = getIntent().getStringExtra("DESCRIPTION");
        String skillDate = getIntent().getStringExtra("DATE");

        skillNameMaterialTextView.setText(skillsName);
        skillDescriptionMaterialTextView.setText(skillsDescription);
        skillDateMaterialTextView.setText(skillDate);
    }

    private void setFindViewsById() {

        skillsNameTextInputLayout = findViewById(R.id.skillsNameTextInputLayoutXml);
        skillsDescriptionTextInputLayout = findViewById(R.id.skillsDescriptionMaterialTextInputLayoutXml);
        skillsDateTextInputLayout = findViewById(R.id.skillsDateTextInputLayoutXml);
        skillNameMaterialTextView = findViewById(R.id.skillsNameMaterialTextViewXml);
        skillDescriptionMaterialTextView = findViewById(R.id.skillsDescriptionMaterialTextViewXml);
        skillDateMaterialTextView = findViewById(R.id.skillsDateCreationMaterialTextViewXml);
        skillUpdateButton = findViewById(R.id.updateDescriptionButtonXml);
        skillDeleteButton = findViewById(R.id.deleteSkillButtonXml);

    }



    private void setUpdateButton() {

        skillUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateDialogBuilder = new AlertDialog.Builder(ParticularSkillActivity.this);
                view = getLayoutInflater().inflate(R.layout.update_skill_button_dialog, null);
                updateDialogBuilder.setView(view);
                updateDialog = updateDialogBuilder.create();
                updateDialog.show();

                updateEditText = view.findViewById(R.id.updateSkillEditTextXml);
                updateSkillDialogMaterialButton = view.findViewById(R.id.saveUpdatedSkillButtonXml);
                cancelUpdatedSkillMaterialButton = view.findViewById(R.id.cancelUpdateButtonXml);

                cancelUpdatedSkillMaterialButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateDialog.dismiss();
                    }
                });

                updateSkillDialogMaterialButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!updateEditText.getText().toString().trim().isEmpty()) {
                            SharedPreferences prefs = getSharedPreferences("skills", MODE_PRIVATE);


                            Skills updatedSkill = new Skills();
                            updatedSkill.setId(prefs.getLong("ID", -1));
                            updatedSkill.setSkillName(prefs.getString("NAME",null));
                            updatedSkill.setDateOfCreation(prefs.getString("DATE",null));
                            updatedSkill.setSkillDescription(updateEditText.getText().toString());

                            SkillsActivityViewmodel updateSkillViewModel = new ViewModelProvider(ParticularSkillActivity.this).get(SkillsActivityViewmodel.class);
                            updateSkillViewModel.updateSkills(String.valueOf(updatedSkill.getId()), updatedSkill);
                            Toast.makeText(ParticularSkillActivity.this,"Skill updated!",Toast.LENGTH_LONG).show();

                            MutableLiveData<Skills> skills=updateSkillViewModel.getSkills(String.valueOf(updatedSkill.getId()));

                            updateDialog.dismiss();

                            skills.observe(ParticularSkillActivity.this, new Observer<Skills>() {
                                @Override
                                public void onChanged(Skills skills) {

                                    skillNameMaterialTextView.setText(skills.getSkillName());
                                    skillDescriptionMaterialTextView.setText(skills.getSkillDescription());
                                    skillDateMaterialTextView.setText(skills.getDateOfCreation());
                                }
                            });

                        }
                    }
                });
            }
        });
    }

    private void setSkillDeleteButton(){

       skillDeleteButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SkillsActivityViewmodel deleteSkillViewModel = new ViewModelProvider(ParticularSkillActivity.this).get(SkillsActivityViewmodel.class);
               deleteSkillViewModel.deleteSkills(String.valueOf(getIntent().getLongExtra("ID",-1)));
               Toast.makeText(ParticularSkillActivity.this,"Skill deleted!",Toast.LENGTH_LONG).show();
               Intent intent=new Intent(ParticularSkillActivity.this, SkillsActivity.class);
               startActivity(intent);
           }
       });
    }

    private void storeIntentExtras() {


        String skillsName = getIntent().getStringExtra("NAME");
        Long skillsId = getIntent().getLongExtra("ID",-1);
        String skillsDescription = getIntent().getStringExtra("DESCRIPTION");
        String skillDate = getIntent().getStringExtra("DATE");
        SharedPreferences.Editor editor = getSharedPreferences("skills", MODE_PRIVATE).edit();
        editor.putString("NAME", skillsName);
        editor.putLong("ID", skillsId);
        editor.putString("DESC", skillsDescription);
        editor.putString("DATE", skillDate);
        editor.apply();

    }
}