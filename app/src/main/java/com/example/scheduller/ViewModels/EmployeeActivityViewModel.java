package com.example.scheduller.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.scheduller.Employees.Employee;
import com.example.scheduller.Repository.EmployeeRepository;
import com.example.scheduller.Repository.SkillsRepository;
import com.example.scheduller.SkillsActivity.Skills;

import java.util.List;

public class EmployeeActivityViewModel extends AndroidViewModel {

    private final EmployeeRepository employeeRepository;

    public EmployeeActivityViewModel(@NonNull Application application) {
        super(application);

        employeeRepository=new EmployeeRepository();
    }

    public MutableLiveData<List<Employee>> getEmployeeList(){
        return  employeeRepository.getEmployeeList();
    }

    public void updateEmployee(Employee employee,String id){
        employeeRepository.updateEmployee(employee,id);
    }

    public void deleteEmployee(String id){
        employeeRepository.deleteEmployee(id);
    }
}
