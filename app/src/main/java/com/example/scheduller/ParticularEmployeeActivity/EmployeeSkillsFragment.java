package com.example.scheduller.ParticularEmployeeActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scheduller.R;
import com.example.scheduller.SkillsActivity.SkillsRecyclerViewAdapter;

import java.util.ArrayList;


public class EmployeeSkillsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private RecyclerView employeeSkillsRecyclerView;
    private EmployeeSkillRecyclerViewAdapter employeeSkillRecyclerViewAdapter;



    public EmployeeSkillsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static EmployeeSkillsFragment newInstance(String skills) {
        EmployeeSkillsFragment fragment = new EmployeeSkillsFragment();
        Bundle args = new Bundle();
        args.putString("SKILLS",skills);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_employee_skills, container, false);

        employeeSkillsRecyclerView = (RecyclerView) view.findViewById(R.id.employeeSkillsRecyclerviewXml);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.VERTICAL, false);
        employeeSkillsRecyclerView.setLayoutManager(manager);

        employeeSkillRecyclerViewAdapter=new EmployeeSkillRecyclerViewAdapter(makeSkillsStringList(getArguments().getString("SKILLS")));
        employeeSkillsRecyclerView.setAdapter(employeeSkillRecyclerViewAdapter);




        return view;
    }

    private ArrayList<String> makeSkillsStringList(String skillsString){


        ArrayList<String> stringArrayList=new ArrayList<>();
        StringBuilder str= new StringBuilder();
        for(int i=0;i<skillsString.length();i++){

            if(skillsString.charAt(i)!=','){

                str.append(skillsString.charAt(i));
            }
            else{
                stringArrayList.add(String.valueOf(str));

                str=new StringBuilder();
            }

        }
         stringArrayList.add(String.valueOf(str));

        return stringArrayList;

    }
}