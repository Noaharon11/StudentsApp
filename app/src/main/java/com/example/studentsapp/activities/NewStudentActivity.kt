package com.example.studentsapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.data.StudentRepository
import com.example.studentsapp.models.Student

class NewStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        // Image Upload Button
        val uploadImageButton = findViewById<Button>(R.id.uploadImageButton)
        uploadImageButton.setOnClickListener {
            // Logic to upload a new image (to be implemented)
            Toast.makeText(this, "Upload Image feature coming soon!", Toast.LENGTH_SHORT).show()
        }


        // Input fields
        val nameInput = findViewById<EditText>(R.id.nameInput)
        val idInput = findViewById<EditText>(R.id.idInput)
        val phoneInput = findViewById<EditText>(R.id.phoneInput)
        val addressInput = findViewById<EditText>(R.id.addressInput)

        // Save button
        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            val name = nameInput.text.toString()
            val id = idInput.text.toString()
            val phone = phoneInput.text.toString()
            val address = addressInput.text.toString()

            if (name.isNotEmpty() && id.isNotEmpty() && phone.isNotEmpty() && address.isNotEmpty()) {
                val newStudent = Student(name, id, phone, address)
                StudentRepository.addStudent(newStudent)
                Toast.makeText(this, "Student added successfully!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Cancel button
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            finish() // Close activity without saving
        }

        // Change Image Button
        uploadImageButton.setOnClickListener {
            // Logic to select a new image (to be implemented)
            Toast.makeText(this, "Change Image feature coming soon!", Toast.LENGTH_SHORT).show()
        }
    }
}
