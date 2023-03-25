package com.example.studentregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Course_Details extends AppCompatActivity {


        private Spinner course_spinner2, year_spinner, semester_spinner;
        private Button btn_save, btn_summary;
        private String first_name, middle_name, last_name, reg_number, gender, school, department, course, id_number, unit_code, unit_name;
        private ArrayList<String> list, unit_list, code_list;
        private ArrayAdapter<String> course_adapter_2;
        private String selectedYear, selectedSemester, uniqueID;
        private String year, sem, year_and_sem, databaseNumber;
        private ArrayAdapter<CharSequence> year_adapter, semester_adapter;
        private TextView unit;
        private ListView unit_code_listView, unit_name_listView;
        private ArrayAdapter code_adapter, unit_adapter;
        private Map<String, Object> map;

        private DatabaseReference reference, push_data_ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details);

        course_spinner2 = findViewById(R.id.course_spinner_2);
        year_spinner = findViewById(R.id.year_spinner);
        semester_spinner = findViewById(R.id.semester_spinner);
        unit = findViewById(R.id.text_view_unit);
        unit_code_listView = findViewById(R.id.code_list_view);
        unit_name_listView = findViewById(R.id.unit_list_view);
        btn_save = findViewById(R.id.btn_save);
        btn_summary = findViewById(R.id.btn_summary);




        first_name = getIntent().getStringExtra("first_name");
        middle_name = getIntent().getStringExtra("middle_name");
        last_name = getIntent().getStringExtra("last_name");
        reg_number = getIntent().getStringExtra("reg_no");
        id_number = getIntent().getStringExtra("id_no");
        gender = getIntent().getStringExtra("gender");
        school = getIntent().getStringExtra("school");
        department = getIntent().getStringExtra("department");
        course = getIntent().getStringExtra("course");

        list = new ArrayList<String>();
        list.add(course);



        year_adapter = ArrayAdapter.createFromResource(this, R.array.years_array, R.layout.spinner_layout);
        year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year_spinner.setAdapter(year_adapter);

        semester_adapter = ArrayAdapter.createFromResource(this, R.array.semester_array, R.layout.spinner_layout);
        semester_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semester_spinner.setAdapter(semester_adapter);


        year_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedYear = year_spinner.getSelectedItem().toString();

                int yearSpinnerID = adapterView.getId();
                if(yearSpinnerID == R.id.year_spinner){
                    switch (selectedYear){
                        case "Select Year":
                            year = "error";
                            break;

                        case "Year 1":
                            year = "1";
                            break;

                        case"Year 2":
                            year = "2";
                            break;

                        case"Year 3":
                            year = "3";
                            break;

                        case"Year 4":
                            year = "4";
                            break;

                        default: break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        semester_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSemester = semester_spinner.getSelectedItem().toString();

                int semSpinnerID = adapterView.getId();
                if (semSpinnerID == R.id.semester_spinner){
                    switch (selectedSemester){
                        case"Select Semester":
                            sem = "error";
                            break;

                        case"Semester 1":
                            sem = "1";
                            break;

                        case "Semester 2":
                            sem = "2";
                            break;

                        default: break;
                    }
                }
                //combine the year and semester

                year_and_sem = String.format("%s.%s",year,sem);
                System.out.println(year_and_sem);

                switch (year_and_sem){
                    case"1.1":
                        databaseNumber = "0";
                        break;

                    case"1.2":
                        databaseNumber = "1";
                        break;

                    case"2.1":
                        databaseNumber = "2";
                        break;

                    case"2.2":
                        databaseNumber = "3";
                        break;

                    case"3.1":
                        databaseNumber = "4";
                        break;

                    case"3.2":
                        databaseNumber = "5";
                        break;

                    case"4.1":
                        databaseNumber = "6";
                        break;

                    case"4.2":
                        databaseNumber = "7";
                        break;
                }

                unit_list = new ArrayList<String>();
                code_list = new ArrayList<String>();

                reference = FirebaseDatabase.getInstance().getReference().child("courses/0/semesters/"+ databaseNumber + "/units");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String list = dataSnapshot.getValue().toString();
//                System.out.println(list);

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            unit_code = snapshot.child("code").getValue().toString();
                            unit_name = snapshot.child("name").getValue().toString();


                            unit_list.add(unit_name);


                            code_list.add(unit_code);


                            System.out.println(unit_list);
                            System.out.println(code_list);



//                     courses_list = new ArrayList<String>();
//                    unit_list = new ArrayList<DataSnapshot>();
//                    unit_list.add(snapshot);




                        }

                        code_adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, code_list);
                        unit_code_listView.setAdapter(code_adapter);

                        unit_adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, unit_list);
                        unit_name_listView .setAdapter(unit_adapter);



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });










        course_adapter_2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        course_adapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        course_spinner2.setAdapter(course_adapter_2);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                uniqueID = UUID.randomUUID().toString();
                push_data_ref = FirebaseDatabase.getInstance().getReference();

                map= new HashMap<>();
                map.put("first_name", first_name);
                map.put("middle_name", middle_name);
                map.put("last_name", last_name);
                map.put("reg_number", reg_number);
                map.put("id_number", id_number);
                map.put("gender", gender);
                map.put("school", school);
                map.put("department",department);
                map.put("course", course);
                map.put("units", unit_list);
                map.put("unit_code", code_list);
                map.put("year", selectedYear);
                map.put("semester", selectedSemester);
                uniqueID = push_data_ref.child("students").push().getKey();
                push_data_ref.child("students/"+uniqueID).setValue(map).addOnCompleteListener(task ->{
                    if(task.isSuccessful()){
                            Toast.makeText(Course_Details.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();


                    } else {
                            Toast.makeText(Course_Details.this, "Error when pushing data", Toast.LENGTH_SHORT).show();
                    }

                });

                System.out.println(uniqueID);

            }

        });

        btn_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Course_Details.this, Summary.class);

                intent.putExtra("id", uniqueID);
                startActivity(intent);

            }
        });



    }
}