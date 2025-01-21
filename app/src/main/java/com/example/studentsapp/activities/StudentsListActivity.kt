package com.example.studentsapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.adapters.StudentsAdapter
import com.example.studentsapp.data.StudentRepository

class StudentsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)

        // Initialize RecyclerView
        val studentsRecyclerView = findViewById<RecyclerView>(R.id.studentsRecyclerView)
        val adapter = StudentsAdapter(
            students = StudentRepository.students,
            onEditClicked = { student ->
                val intent = Intent(this, EditStudentActivity::class.java)
                intent.putExtra("student", student)
                startActivity(intent)
            },
            onStudentClicked = { student ->
                val intent = Intent(this, StudentDetailsActivity::class.java)
                intent.putExtra("student", student)
                startActivity(intent)
            }
        )

        studentsRecyclerView.layoutManager = LinearLayoutManager(this)
        studentsRecyclerView.adapter = adapter

        // Initialize Add Button
        val addStudentButton = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.addStudentButton)
        addStudentButton.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        findViewById<RecyclerView>(R.id.studentsRecyclerView).adapter?.notifyDataSetChanged()
    }
}
