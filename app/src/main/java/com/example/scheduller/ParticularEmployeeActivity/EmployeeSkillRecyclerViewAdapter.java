package com.example.scheduller.ParticularEmployeeActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scheduller.R;
import com.example.scheduller.SkillsActivity.SkillsRecyclerViewAdapter;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSkillRecyclerViewAdapter extends RecyclerView.Adapter<EmployeeSkillRecyclerViewAdapter.EmployeeSkillsViewHolder> {

    ArrayList<String> stringList;

    public EmployeeSkillRecyclerViewAdapter(ArrayList<String> stringList) {
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public EmployeeSkillRecyclerViewAdapter.EmployeeSkillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_skills_row, parent, false);
        return new EmployeeSkillsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeSkillRecyclerViewAdapter.EmployeeSkillsViewHolder holder, int position) {

        holder.employeeSkillMaterialTextView.setText(stringList.get(holder.getAdapterPosition()));


    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class EmployeeSkillsViewHolder extends RecyclerView.ViewHolder {

        CardView employeeSkillCardView;
        MaterialTextView employeeSkillMaterialTextView;
        public EmployeeSkillsViewHolder(@NonNull View itemView) {
            super(itemView);

            employeeSkillCardView=itemView.findViewById(R.id.employeeSkillCardViewXml);
            employeeSkillMaterialTextView=itemView.findViewById(R.id.employeeSkillMaterialTextViewXml);
        }
    }
}
