package com.example.scheduller.Employees;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scheduller.ParticularEmployeeActivity.ParticularEmployeeActivity;
import com.example.scheduller.ParticularSkillActivity.ParticularSkillActivity;
import com.example.scheduller.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRecyclerViewAdapter extends RecyclerView.Adapter<EmployeeRecyclerViewAdapter.EmployeeViewHolder> {

    List<Employee> employeeList;
    Context context;

    public EmployeeRecyclerViewAdapter(List<Employee> employeeList, Context context) {
        this.employeeList = employeeList;
        this.context = context;
    }

    @NonNull
    @Override
    public EmployeeRecyclerViewAdapter.EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_row, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeRecyclerViewAdapter.EmployeeViewHolder holder, int position) {

        holder.employeeMaterialTextView.setText(String.valueOf(employeeList.get(holder.getAdapterPosition()).getEmployeeSurname()
                +"  "+employeeList.get(holder.getAdapterPosition()).getEmployeeName()));
        holder.employeeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, ParticularEmployeeActivity.class);
                intent.putExtra("NAME",employeeList.get(holder.getAdapterPosition()).getEmployeeName());
                intent.putExtra("ID",employeeList.get(holder.getAdapterPosition()).getId());
                intent.putExtra("SURNAME",employeeList.get(holder.getAdapterPosition()).getEmployeeSurname());
                intent.putExtra("HIRINGDATE",employeeList.get(holder.getAdapterPosition()).getEmployeeHiringDate());
                intent.putExtra("SKILLS",  employeeList.get(holder.getAdapterPosition()).getEmployeeSkills());

                (context).startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder{

        CardView employeeCardView;
        MaterialTextView employeeMaterialTextView;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            employeeMaterialTextView=itemView.findViewById(R.id.employeeRowXml);
            employeeCardView=itemView.findViewById(R.id.employeeCardViewXml);
        }
    }
}
