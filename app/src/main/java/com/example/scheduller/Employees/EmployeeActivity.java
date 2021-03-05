package com.example.scheduller.Employees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.scheduller.ParticularEmployeeActivity.ParticularEmployeeActivity;
import com.example.scheduller.ParticularSkillActivity.ParticularSkillActivity;
import com.example.scheduller.R;
import com.example.scheduller.SkillsActivity.Skills;
import com.example.scheduller.SkillsActivity.SkillsRecyclerViewAdapter;
import com.example.scheduller.ViewModels.EmployeeActivityViewModel;
import com.example.scheduller.ViewModels.SkillsActivityViewmodel;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity {

    private RecyclerView employeeRecyclerView;
    private EmployeeRecyclerViewAdapter employeeRecyclerViewAdapter;
    private List<Employee> listOfEmployees=new ArrayList<>();
    private Toolbar employeesToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        setEmployeeToolBar();
        setEmployeeRecyclerView();

    }

    private void setEmployeeToolBar(){

        employeesToolBar=findViewById(R.id.employeeToolbarXml);
        setSupportActionBar(employeesToolBar);
        getSupportActionBar().setTitle(R.string.employees_string);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setEmployeeRecyclerView() {

        employeeRecyclerView = findViewById(R.id.employeeRecyclerviewXml);
        employeeRecyclerView.setHasFixedSize(true);
        employeeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        EmployeeActivityViewModel employeeActivityViewModel= new ViewModelProvider(this).get(EmployeeActivityViewModel.class);
        employeeActivityViewModel.getEmployeeList().observe(this,new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employeeList) {
                if (employeeList != null) {
                    listOfEmployees.addAll(employeeList);
                    employeeRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });

        employeeRecyclerViewAdapter=new EmployeeRecyclerViewAdapter(listOfEmployees,EmployeeActivity.this);
        employeeRecyclerView.setAdapter(employeeRecyclerViewAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                employeeActivityViewModel.deleteEmployee(String.valueOf(listOfEmployees.get(viewHolder.getAdapterPosition()).getId()));
                Toast.makeText(EmployeeActivity.this,"Employee deleted!",Toast.LENGTH_LONG).show();

            }
        }).attachToRecyclerView(employeeRecyclerView);

    }


}