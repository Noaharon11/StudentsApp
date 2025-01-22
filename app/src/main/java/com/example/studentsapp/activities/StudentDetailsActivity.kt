package com.example.studentsapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.data.StudentRepository
import com.example.studentsapp.models.Student

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var studentImageView: ImageView
    private lateinit var studentNameTextView: TextView
    private lateinit var studentIdTextView: TextView
    private lateinit var studentPhoneTextView: TextView
    private lateinit var studentAddressTextView: TextView
    private lateinit var editButton: Button
    private lateinit var deleteButton: Button
    private lateinit var backButton: Button

    private var currentStudent: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        // Initialize views
        studentImageView = findViewById(R.id.studentImageView)
        studentNameTextView = findViewById(R.id.studentNameTextView)
        studentIdTextView = findViewById(R.id.studentIdTextView)
        studentPhoneTextView = findViewById(R.id.studentPhoneTextView)
        studentAddressTextView = findViewById(R.id.studentAddressTextView)
        editButton = findViewById(R.id.editButton)
        deleteButton = findViewById(R.id.deleteButton)
        backButton = findViewById(R.id.backButton)

        // Get student data from intent
        currentStudent = intent.getParcelableExtra("student")
        currentStudent?.let { student ->
            studentNameTextView.text = "Name: ${student.name}"
            studentIdTextView.text = "ID: ${student.id}"
            studentPhoneTextView.text = "Phone: ${student.phone}"
            studentAddressTextView.text = "Address: ${student.address}"
            studentImageView.setImageResource(R.drawable.default_student_image)
        }


        // Edit button
        editButton.setOnClickListener {
            currentStudent?.let { student ->
                val intent = Intent(this, EditStudentActivity::class.java)
                intent.putExtra("student", student)
                startActivity(intent)
                finish()
            }
        }

        // Delete button
        deleteButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Delete Student")
                .setMessage("Are you sure you want to delete this student?")
                .setPositiveButton("Yes") { _, _ ->
                    currentStudent?.let { student ->
                        StudentRepository.deleteStudent(student)
                        Toast.makeText(this, "Student deleted!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
                .setNegativeButton("No", null)
                .show()
        }

        // Back button
        backButton.setOnClickListener {
            finish()
        }
    }
}
