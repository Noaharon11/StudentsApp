package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.activities.StudentsListActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Navigate to the Students List Activity
        val intent = Intent(this, StudentsListActivity::class.java)
        startActivity(intent)
        finish() // Close the MainActivity
    }
}
