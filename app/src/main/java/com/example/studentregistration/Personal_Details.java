package com.example.studentregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Personal_Details extends AppCompatActivity {

    private String selectedSchool, selectedDepartment, selectedCourse;
    private TextView first_name, middle_name, last_name, id_number, reg_number;
    private Spinner school_spinner, department_spinner, course_spinner;
    private Button btn_submit, btn_cancel;
    private ArrayAdapter<CharSequence> school_adapter, department_adapter, course_adapter;
    private String f_name, m_name, l_name, reg_no, gender, id_no;
    private RadioGroup radioGroup;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_details);


        first_name = findViewById(R.id.first_name);
        middle_name = findViewById(R.id.middle_name);
        last_name = findViewById(R.id.last_name);
        id_number = findViewById(R.id.id_number);
        reg_number = findViewById(R.id.reg_number);

        school_spinner = findViewById(R.id.school_spinner);
        department_spinner = findViewById(R.id.department_spinner);
        course_spinner = findViewById(R.id.course_spinner);

        btn_cancel = findViewById(R.id.btn_cancel);

        radioGroup = findViewById(R.id.radioGroup);


        school_adapter = ArrayAdapter.createFromResource(this, R.array.school_array, R.layout.spinner_layout);

        school_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        school_spinner.setAdapter(school_adapter);

        school_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedSchool = school_spinner.getSelectedItem().toString();

                int schoolSpinnerID = adapterView.getId();
                if (schoolSpinnerID == R.id.school_spinner) {
                    switch (selectedSchool) {

                        case "Select The School":
                            department_adapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.default_Department, R.layout.spinner_layout);
                            break;

                        case "School of Business":
                            department_adapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.business_departments, R.layout.spinner_layout);
                            break;


                        case "School of Engineering":
                            department_adapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.engineering_departments, R.layout.spinner_layout);
                            break;


                        case "School of Computer Science and IT":
                            department_adapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.CS_and_IT_departments, R.layout.spinner_layout);
                            break;

                        case "School of Science":
                            department_adapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.science_departments, R.layout.spinner_layout);
                            break;


                        default:
                            break;

                    }
                    department_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    department_spinner.setAdapter(department_adapter);

                    department_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedDepartment = department_spinner.getSelectedItem().toString();

                            int departmentSpinnerID = adapterView.getId();
                            if (departmentSpinnerID == R.id.department_spinner) {
                                switch (selectedDepartment) {
                                    case "Select the Department":
                                        course_adapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.default_course, R.layout.spinner_layout);
                                        break;

                                    case "Department of Accounting":
                                        course_adapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.accounting_department, R.layout.spinner_layout);
                                        break;

                                    case "Department of Commerce":
                                        course_adapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.commerce_department, R.layout.spinner_layout);
                                        break;

                                    case "Department of Finance":
                                        course_adapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.finance_department, R.layout.spinner_layout);
                                        break;

                                    case "Department of Mechatronics Engineering":
                                        course_adapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.mechatronics_department, R.layout.spinner_layout);
                                        break;

                                    case "Department of Mechanical Engineering":
                                        course_adapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.mechanical_department, R.layout.spinner_layout);
                                        break;

                                    case "Department of Chemical Engineering":
                                        course_adapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.chemical_department, R.layout.spinner_layout);
                                        break;

                                    case "Department of Computer Science":
                                        course_adapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.cs_department, R.layout.spinner_layout);
                                        break;

                                    case "Department of IT":
                                        course_adapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.IT_department, R.layout.spinner_layout);
                                        break;

                                    case "Department of Nursing":
                                        course_adapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.nursing_department, R.layout.spinner_layout);
                                        break;

                                    case "Department of Actuarial Science":
                                        course_adapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.actuarial_department, R.layout.spinner_layout);
                                        break;


                                    default:
                                        break;
                                }

                                course_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                course_spinner.setAdapter(course_adapter);

                                course_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        selectedCourse = course_spinner.getSelectedItem().toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                f_name = first_name.getText().toString();
                m_name = middle_name.getText().toString();
                l_name = last_name.getText().toString();
                reg_no = reg_number.getText().toString();
                id_no = id_number.getText().toString();

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i) {
                            case R.id.male_radio_btn:
                                gender = "male";
                                break;

                            case R.id.female_radio_btn:
                                gender = "female";
                                break;
                        }
                    }
                });


                Intent i = new Intent(Personal_Details.this, Course_Details.class);
                i.putExtra("first_name", f_name);
                i.putExtra("middle_name", m_name);
                i.putExtra("last_name", l_name);
                i.putExtra("reg_no", reg_no);
                i.putExtra("id_number", id_no);
                i.putExtra("gender", gender);
                i.putExtra("school", selectedSchool);
                i.putExtra("department", selectedDepartment);
                i.putExtra("course", selectedCourse);
                startActivity(i);
            }
        });

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference = FirebaseDatabase.getInstance().getReference().child("/courses/0/semesters");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String list = dataSnapshot.getValue().toString();
                        System.out.println(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

            }
        });
    }
}