package com.example.scheduller.ParticularEmployeeActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.scheduller.Employees.Employee;
import com.example.scheduller.ParticularSkillActivity.ParticularSkillActivity;
import com.example.scheduller.R;
import com.example.scheduller.Repository.EmployeeRepository;
import com.example.scheduller.ViewModels.EmployeeActivityViewModel;
import com.example.scheduller.ViewModels.SkillsActivityViewmodel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;


public class EmployeeDetailsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    private MaterialTextView employeeNameMaterialTextView;
    private MaterialTextView employeeSurnameMaterialTextView;
    private static MaterialTextView employeeHiringDateMaterialTextView;
    private MaterialButton updateEmployeeDetailsMaterialButton;
    private AlertDialog updateEmployeeDialog;
    private AlertDialog.Builder updateEmployeeDialogBuilder;
    private TextInputEditText updateEmployeeNameEditText;
    private TextInputEditText updateEmployeeSurnameEditText;
    private MaterialButton saveEmployeeDialogMaterialButton;
    private MaterialButton cancelUpdateEmployeeMaterialButton;

    public EmployeeDetailsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static EmployeeDetailsFragment newInstance(String name,String surname,String hiringDate,Long id,String skills) {

        EmployeeDetailsFragment fragment = new EmployeeDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM5,skills);
        args.putLong(ARG_PARAM4,id);
        args.putString(ARG_PARAM1, name);
        args.putString(ARG_PARAM2, surname);
        args.putString(ARG_PARAM3,hiringDate);
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_employee_details, container, false);
        employeeNameMaterialTextView = view.findViewById(R.id.employeeNameMaterialTextViewXml);
        employeeSurnameMaterialTextView = view.findViewById(R.id.employeeSurnameMaterialTextViewXml);
        employeeHiringDateMaterialTextView = view.findViewById(R.id.employeeHiringDateMaterialTextViewXml);
        updateEmployeeDetailsMaterialButton=view.findViewById(R.id.updateEmployeeDetailsEmployeeButtonXml);

        updateEmployeeDetailsMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setUpdateDialog(v);
            }
        });
        employeeNameMaterialTextView.setText(getArguments().getString(ARG_PARAM1));
        employeeSurnameMaterialTextView.setText(getArguments().getString(ARG_PARAM2));
        employeeHiringDateMaterialTextView.setText(getArguments().getString(ARG_PARAM3));
        return view;
    }

    private void setUpdateDialog(View view){

        updateEmployeeDialogBuilder = new AlertDialog.Builder(getActivity());
        view = getLayoutInflater().inflate(R.layout.update_employee_details_dialog, null);
        updateEmployeeDialogBuilder.setView(view);
        updateEmployeeDialog = updateEmployeeDialogBuilder.create();
        updateEmployeeDialog.show();

        updateEmployeeNameEditText = view.findViewById(R.id.updateEmployeeNameEditTextXml);
        updateEmployeeSurnameEditText=view.findViewById(R.id.updateEmployeeSurNameEditTextXml);
        saveEmployeeDialogMaterialButton=view.findViewById(R.id.saveUpdatedEmployeeDetailsXml);
        saveEmployeeDialogMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!updateEmployeeNameEditText.getText().toString().trim().isEmpty()&&
                !updateEmployeeSurnameEditText.getText().toString().trim().isEmpty()){

                    Employee updatedEmployee=new Employee();

                    updatedEmployee.setEmployeeName(updateEmployeeNameEditText.getText().toString().trim());
                    updatedEmployee.setEmployeeSurname(updateEmployeeSurnameEditText.getText().toString());
                    updatedEmployee.setEmployeeHiringDate(getArguments().getString(ARG_PARAM3));
                    updatedEmployee.setId(getArguments().getLong(ARG_PARAM4));
                    updatedEmployee.setEmployeeSkills(getArguments().getString(ARG_PARAM5));

                    EmployeeRepository employeeRepository=new EmployeeRepository();
                    employeeRepository.updateEmployee(updatedEmployee,String.valueOf(updatedEmployee.getId()));

                    Toast.makeText(getActivity(),"Employee updated!",Toast.LENGTH_LONG).show();
                    updateEmployeeDialog.dismiss();
                }

            }
        });
        cancelUpdateEmployeeMaterialButton=view.findViewById(R.id.cancelUpdateEmployeeButtonXml);
        cancelUpdateEmployeeMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployeeDialog.dismiss();
            }
        });
    }
}