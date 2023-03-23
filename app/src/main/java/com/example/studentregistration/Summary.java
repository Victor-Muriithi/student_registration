package com.example.studentregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Summary extends AppCompatActivity {
    String unit_name, first_name, last_name, middle_name, Reg_number, Id_number, School, Department, Course, Year, Semester, Units, Name;
    private TextView name, reg_number, id_number, school, department, course, year, semester;
    private ListView units;
    private ArrayList<String> unit_list;
    private ArrayList<DataSnapshot> list_dataSnapShot;
    private ArrayAdapter<String> unit_adapter;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);

        name = findViewById(R.id.name_textView);
        reg_number = findViewById(R.id.regNo_textView);
        id_number = findViewById(R.id.id_textView);
        school = findViewById(R.id.school_textView);
        department = findViewById(R.id.department_textView);
        course = findViewById(R.id.course_textView);
        year = findViewById(R.id.year_textView);
        semester = findViewById(R.id.semester_textView);
        units = findViewById(R.id.unit_listView);


        unit_list = new ArrayList<String>();

        reference = FirebaseDatabase.getInstance().getReference("students");
        reference.child("-NQiZIxKXgQD4zu7jXwi").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();


                try {

//                    for (DataSnapshot snapshot : task.getChildren()) {
//                        unit_name = snapshot.child("units").getValue().toString();
//                        System.out.println(unit_name);
//                        unit_list.add(unit_name);
//                    }

                    first_name = String.valueOf(dataSnapshot.child("first_name").getValue());
                    last_name = dataSnapshot.child("last_name").getValue().toString();
                    middle_name = dataSnapshot.child("middle_name").getValue().toString();
                    Id_number = dataSnapshot.child("id_number").getValue().toString();
                    Reg_number = dataSnapshot.child("reg_number").getValue().toString();
                    School = dataSnapshot.child("school").getValue().toString();
                    Department = dataSnapshot.child("department").getValue().toString();
                    Course = dataSnapshot.child("course").getValue().toString();
                    Year = dataSnapshot.child("year").getValue().toString();
                    Semester = dataSnapshot.child("semester").getValue().toString();


                    Name = first_name + " " + middle_name + " " + last_name + " ";

                    name.setText(Name);
                    reg_number.setText(Reg_number);
                    id_number.setText(Id_number);
                    school.setText(School);
                    department.setText(Department);
                    course.setText(Course);
                    year.setText(Year);
                    semester.setText(Semester);


                    System.out.println(unit_list);
                    unit_adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, unit_list);
                    unit_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    units.setAdapter(unit_adapter);

                } catch (Exception e) {
                }


            }

        });


    }
}