package com.example.scheduller.ParticularEmployeeActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.scheduller.R;
import com.example.scheduller.ViewModels.EmployeeActivityViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParticularEmployeeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Toolbar employeeToolbar;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_employee);

            setEmployeeToolbar();
            setFragments();


    }

    private void setEmployeeToolbar(){

        employeeToolbar = (Toolbar) findViewById(R.id.particularEmployeeToolbarXml);

        setSupportActionBar(employeeToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getIntent().getStringExtra("SURNAME")+" "+
                getIntent().getStringExtra("NAME") );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setFragments(){

        viewPager = (ViewPager) findViewById(R.id.pagerEmployee);
        tabLayout = (TabLayout) findViewById(R.id.tabEmployeeLayout);
        Bundle bundle= new Bundle();

        bundle.putString("NAME",getIntent().getStringExtra("NAME"));
        bundle.putString("SURNAME",getIntent().getStringExtra("SURNAME"));
        bundle.putString("HIRINGDATE",getIntent().getStringExtra("HIRINGDATE"));
        bundle.putString("SKILLS",getIntent().getStringExtra("SKILLS"));
        bundle.putLong("ID",getIntent().getLongExtra("ID",-1));
        Fragment employeeDetailsFragment = EmployeeDetailsFragment.newInstance( bundle.getString("NAME"),bundle.getString("SURNAME"),
                bundle.getString("HIRINGDATE"),bundle.getLong("ID"),bundle.getString("SKILLS"));
        fragmentList.add(employeeDetailsFragment);

        Fragment employeeSkillsFragment=EmployeeSkillsFragment.newInstance(bundle.getString("SKILLS"));
        fragmentList.add(employeeSkillsFragment);

        EmployeeFragmentAdapter pagerAdapter = new EmployeeFragmentAdapter(getSupportFragmentManager(),
                getApplicationContext(), fragmentList);



        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Employee details");
        tabLayout.getTabAt(1).setText("Employee skills");
    }

}