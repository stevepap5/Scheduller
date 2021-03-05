package com.example.scheduller.Repository;

import android.util.Base64;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.scheduller.Api.EmployeeApi;
import com.example.scheduller.Api.SkillsApi;
import com.example.scheduller.Employees.Employee;
import com.example.scheduller.Retrofit.EmployeeRetrofitService;
import com.example.scheduller.Retrofit.SkillsRetrofitService;
import com.example.scheduller.SkillsActivity.Skills;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRepository {

    private final MutableLiveData<List<Employee>> listOfEmployees= new MutableLiveData<>();
    private String username = "stefanos";
    private String password = "11";
    private String base = username + ":" + password;
    private String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
    private static EmployeeApi employeeApi;
   

    private static SkillsRepository skillsRepository;

    public static SkillsRepository getInstance() {
        if (skillsRepository == null) {
            skillsRepository = new SkillsRepository();
        }
        return skillsRepository;
    }

    public EmployeeRepository() {
        employeeApi = EmployeeRetrofitService.getInterface();
    }

    public MutableLiveData<List<Employee>> getEmployeeList() {

        Call<List<Employee>> call = employeeApi.getEmployeesList(authHeader);
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                 List<Employee> employeeList=response.body();
                 Collections.sort(employeeList, new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {

                        return o1.getEmployeeSurname().compareTo(o2.getEmployeeSurname());
                    }
                });
                listOfEmployees.setValue(employeeList);

            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

            }
        });


        return listOfEmployees;
    }


    public void updateEmployee(Employee employee,String id){

        Call<Employee> call = employeeApi.updateEmployee(authHeader,id,employee);
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.i("UPDATEEMPLOYEE",t.getMessage());
            }
        });
    }

    public void deleteEmployee(String id){

        Call<Employee> call = employeeApi.deleteEmployee(authHeader,id);
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.i("DELETEEMPLOYEE",t.getMessage());
            }
        });
    }
}
