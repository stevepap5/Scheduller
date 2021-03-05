package com.example.scheduller.Api;

import com.example.scheduller.Employees.Employee;
import com.example.scheduller.SkillsActivity.Skills;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeApi {

    @GET("/employee")
    Call<List<Employee>> getEmployeesList(@Header("Authorization") String authHeader);

    @GET("/employee/{id}")
    Call<Employee> getEmployee();

    @POST("/employee")
    Call<Employee> createEmployee();

    @PUT("/employee/{id}")
    Call<Employee> updateEmployee(@Header("Authorization") String authHeader, @Path("id") String id, @Body Employee employee);

    @DELETE("/employee/{id}")
    Call<Employee> deleteEmployee(@Header("Authorization") String authHeader,@Path("id") String id);
}
