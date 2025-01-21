package com.example.studentsapp.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.data.StudentRepository
import com.example.studentsapp.models.Student

class EditStudentActivity : AppCompatActivity() {

    private lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        // Fetch student details from intent
        student = intent.getSerializableExtra("student") as Student

        // Initialize views
        val studentImageView = findViewById<ImageView>(R.id.studentImageView)
        val nameInput = findViewById<EditText>(R.id.nameInput)
        val idInput = findViewById<EditText>(R.id.idInput)
        val phoneInput = findViewById<EditText>(R.id.phoneInput)
        val addressInput = findViewById<EditText>(R.id.addressInput)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)

        // Populate fields with student data
        studentImageView.setImageResource(R.drawable.default_student_image) // Default or loaded image
        nameInput.setText(student.name)
        idInput.setText(student.id)
        phoneInput.setText(student.phone)
        addressInput.setText(student.address)

        // Save button click
        saveButton.setOnClickListener {
            val updatedName = nameInput.text.toString()
            val updatedId = idInput.text.toString()
            val updatedPhone = phoneInput.text.toString()
            val updatedAddress = addressInput.text.toString()

            if (updatedName.isNotEmpty() && updatedId.isNotEmpty() && updatedPhone.isNotEmpty() && updatedAddress.isNotEmpty()) {
                // Update the student object
                val updatedStudent = student.copy(
                    name = updatedName,
                    id = updatedId,
                    phone = updatedPhone,
                    address = updatedAddress
                )

                // Update in repository
                StudentRepository.updateStudent(updatedStudent)
                Toast.makeText(this, "Student updated successfully!", Toast.LENGTH_SHORT).show()
                finish() // Return to the previous screen
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Delete button click
        deleteButton.setOnClickListener {
            StudentRepository.deleteStudent(student)
            Toast.makeText(this, "Student deleted successfully!", Toast.LENGTH_SHORT).show()
            finish() // Return to the previous screen
        }

        // Cancel button click
        cancelButton.setOnClickListener {
            finish() // Return to the previous screen without saving
        }
    }
}
