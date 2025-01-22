package com.example.studentsapp.activities

import android.content.DialogInterface
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.data.StudentRepository
import com.example.studentsapp.models.Student

class NewStudentActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var idInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var addressInput: EditText
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button
    private lateinit var studentImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        // Initialize views
        nameInput = findViewById(R.id.nameInput)
        idInput = findViewById(R.id.idInput)
        phoneInput = findViewById(R.id.phoneInput)
        addressInput = findViewById(R.id.addressInput)
        saveButton = findViewById(R.id.saveButton)
        cancelButton = findViewById(R.id.cancelButton)
        studentImageView = findViewById(R.id.studentImageView)

        // Save button logic
        saveButton.setOnClickListener {
            val name = nameInput.text.toString()
            val id = idInput.text.toString()
            val phone = phoneInput.text.toString()
            val address = addressInput.text.toString()

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

            // Add new student to the repository
            val student = Student(
                id = id,
                name = name,
                phone = phone,
                address = address,
            )
            StudentRepository.addStudent(student)

            Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show()
            android.os.Handler(Looper.getMainLooper()).postDelayed({
                finish()
            }, 1000) // 1000 milliseconds = 1 second
        }

        // Cancel button logic
        cancelButton.setOnClickListener {
            if (isAnyFieldFilled()) {
                showCancelConfirmationDialog()
            } else {
                finish()
            }
        }
    }

    // Check if any field is filled
    private fun isAnyFieldFilled(): Boolean {
        return nameInput.text.isNotEmpty() ||
                idInput.text.isNotEmpty() ||
                phoneInput.text.isNotEmpty() ||
                addressInput.text.isNotEmpty()
    }

    // Show confirmation dialog on cancel
    private fun showCancelConfirmationDialog() {
        AlertDialog.Builder(this)
            .setMessage("Are you sure you want to discard changes?")
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
