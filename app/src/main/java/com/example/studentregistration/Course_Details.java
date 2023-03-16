package com.example.studentregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course_Details extends AppCompatActivity {


        private Spinner course_spinner2, year_spinner, semester_spinner;
        private String first_name, middle_name, last_name, reg_number, gender, school, department, course, id_number, unit_code, unit_name;
        private ArrayList<String> list, unit_list, code_list ;
        private ArrayAdapter<String> course_adapter_2;
        private TextView unit;
        private ListView unit_code_listView, unit_name_listView;
        private ArrayAdapter code_adapter, unit_adapter;

        private DatabaseReference reference;


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
        code_list = new ArrayList<String>();
        list.add(course);
        unit_list = new ArrayList<String>();

        reference = FirebaseDatabase.getInstance().getReference().child("courses/0/semesters/0/units");
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








        course_adapter_2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        course_adapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        course_spinner2.setAdapter(course_adapter_2);






    }
}