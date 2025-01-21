package com.example.studentsapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.models.Student
import com.example.studentsapp.data.StudentRepository

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        // Fetch student details from intent
        student = intent.getSerializableExtra("student") as Student

        // Initialize views
        val studentImageView = findViewById<ImageView>(R.id.studentImageView)
        val studentNameTextView = findViewById<TextView>(R.id.studentNameTextView)
        val studentIdTextView = findViewById<TextView>(R.id.studentIdTextView)
        val studentPhoneTextView = findViewById<TextView>(R.id.studentPhoneTextView)
        val studentAddressTextView = findViewById<TextView>(R.id.studentAddressTextView)
        val editButton = findViewById<Button>(R.id.editButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val backButton = findViewById<Button>(R.id.backButton)

        // Populate data
        studentImageView.setImageResource(R.drawable.default_student_image)
        studentNameTextView.text = "Name: ${student.name}"
        studentIdTextView.text = "ID: ${student.id}"
        studentPhoneTextView.text = "Phone: ${student.phone}"
        studentAddressTextView.text = "Address: ${student.address}"

        // Edit button click
        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student", student)
            startActivity(intent)
        }

        // Delete button click
        deleteButton.setOnClickListener {
            StudentRepository.deleteStudent(student)
            Toast.makeText(this, "Student deleted successfully!", Toast.LENGTH_SHORT).show()
            finish()
        }

        // Back button click
        backButton.setOnClickListener {
            finish() // Return to the main list
        }
    }
}
