package com.example.studentsapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.data.StudentRepository
import com.example.studentsapp.models.Student

class EditStudentActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var idInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var addressInput: EditText
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button
    private lateinit var cancelButton: Button
    private lateinit var studentImageView: ImageView
    private var currentStudent: Student? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        // Initialize views
        nameInput = findViewById(R.id.nameInput)
        idInput = findViewById(R.id.idInput)
        phoneInput = findViewById(R.id.phoneInput)
        addressInput = findViewById(R.id.addressInput)
        saveButton = findViewById(R.id.saveButton)
        deleteButton = findViewById(R.id.deleteButton)
        cancelButton = findViewById(R.id.cancelButton)
        studentImageView = findViewById(R.id.studentImageView)

        // Get student from intent
        currentStudent = intent.getParcelableExtra("student")
        currentStudent?.let { student ->
            nameInput.setText(student.name)
            idInput.setText(student.id)
            phoneInput.setText(student.phone)
            addressInput.setText(student.address)
            if (student.imageUri.isNotEmpty()) {
                studentImageView.setImageURI(android.net.Uri.parse(student.imageUri))
            }
        }


        // Save button
        saveButton.setOnClickListener {
            val name = nameInput.text.toString()
            val id = idInput.text.toString()
            val phone = phoneInput.text.toString()
            val address = addressInput.text.toString()

            // Validate empty fields
            if (name.isEmpty()) {
                nameInput.error = "Name cannot be empty"
                nameInput.requestFocus()
                return@setOnClickListener
            }
            if (id.isEmpty()) {
                idInput.error = "ID cannot be empty"
                idInput.requestFocus()
                return@setOnClickListener
            }
            if (phone.isEmpty()) {
                phoneInput.error = "Phone cannot be empty"
                phoneInput.requestFocus()
                return@setOnClickListener
            }
            if (address.isEmpty()) {
                addressInput.error = "Address cannot be empty"
                addressInput.requestFocus()
                return@setOnClickListener
            }

            if (StudentRepository.getStudentById(id) != null) {
                idInput.error = "ID already exists"
                idInput.requestFocus()
                return@setOnClickListener
            }

            val updatedStudent = currentStudent?.copy(
                name = name,
                id = id,
                phone = phone,
                address = address
            )

            if (updatedStudent != null) {
                StudentRepository.updateStudent(updatedStudent, oldId = currentStudent!!.id)
                Toast.makeText(this, "Student updated successfully!", Toast.LENGTH_SHORT).show()
                android.os.Handler(Looper.getMainLooper()).postDelayed({
                    finish()
                }, 1000) // 1000 milliseconds = 1 second
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

        // Cancel button
        cancelButton.setOnClickListener {
            if (isStudentDataChanged()) {
                showCancelConfirmationDialog()
            } else {
                finish()
            }
        }
    }

    private fun isStudentDataChanged(): Boolean {
        return currentStudent?.let { student ->
            student.name != nameInput.text.toString() ||
                    student.id != idInput.text.toString() ||
                    student.phone != phoneInput.text.toString() ||
                    student.address != addressInput.text.toString()
        } ?: false
    }

    private fun showCancelConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Unsaved Changes")
            .setMessage("You have unsaved changes. Are you sure you want to cancel?")
            .setPositiveButton("Yes") { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

}
