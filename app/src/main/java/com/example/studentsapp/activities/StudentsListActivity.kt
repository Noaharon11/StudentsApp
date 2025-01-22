package com.example.studentsapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.adapters.StudentsAdapter
import com.example.studentsapp.data.StudentRepository

class StudentsListActivity : AppCompatActivity() {

    private lateinit var studentsRecyclerView: RecyclerView
    private lateinit var addStudentButton: ImageButton
    private lateinit var adapter: StudentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)

        // Initialize views
        studentsRecyclerView = findViewById(R.id.studentsRecyclerView)
        addStudentButton = findViewById(R.id.addStudentButton)

        // Initialize RecyclerView
        studentsRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentsAdapter(StudentRepository.getAllStudents().toMutableList()) { student ->
            // Edit button click
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student", student)
            startActivity(intent)
        }
        studentsRecyclerView.adapter = adapter

        // Add Student Button click
        addStudentButton.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Update the adapter with the latest data from the repository
        adapter.updateData(StudentRepository.getAllStudents())
    }

}
