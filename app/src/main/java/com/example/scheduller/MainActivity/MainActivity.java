package com.example.scheduller.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.scheduller.Employees.EmployeeActivity;
import com.example.scheduller.R;
import com.example.scheduller.SkillsActivity.SkillsActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialButton skillsMaterialButton;
    private MaterialButton employeeMaterialButton;
    private Toolbar mainToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMainToolBar();
        setFindViewsById();
        setClicksOfMaterialButtons();

    }

    private void setMainToolBar(){

        mainToolBar=findViewById(R.id.mainToolbarXml);
        setSupportActionBar(mainToolBar);
        getSupportActionBar().setTitle(R.string.scheduller);

    }

    private void setFindViewsById(){

        skillsMaterialButton=findViewById(R.id.skillsMaterialButtonXml);
        employeeMaterialButton=findViewById(R.id.employeeMaterialButtonXml);
    }

    private void setClicksOfMaterialButtons(){

        skillsMaterialButton.setOnClickListener(this);
        employeeMaterialButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.skillsMaterialButtonXml:

                Intent intentSkills=new Intent(MainActivity.this, SkillsActivity.class);
                startActivity(intentSkills);
                // code block
                break;
            case R.id.employeeMaterialButtonXml:

                Intent intentEmployee=new Intent(MainActivity.this, EmployeeActivity.class);
                startActivity(intentEmployee);
                // code block
                break;
            default:
                // code block
        }

    }


}