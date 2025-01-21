package com.example.studentsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.models.Student

class StudentsAdapter(
    private val students: List<Student>, // The list of students to display
    private val onEditClicked: (Student) -> Unit, // Callback for edit button click
    private val onStudentClicked: (Student) -> Unit // Callback for clicking on a student row
) : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student)
    }

    override fun getItemCount(): Int = students.size

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studentImageView: ImageView = itemView.findViewById(R.id.studentImageView)
        private val studentNameTextView: TextView = itemView.findViewById(R.id.studentNameTextView)
        private val studentIdTextView: TextView = itemView.findViewById(R.id.studentIdTextView)
        private val studentCheckBox: CheckBox = itemView.findViewById(R.id.statusCheckBox)
        private val editButton: ImageButton = itemView.findViewById(R.id.editButton)

        fun bind(student: Student) {
            // Set student data
            studentImageView.setImageResource(R.drawable.default_student_image) // Default image
            studentNameTextView.text = student.name
            studentIdTextView.text = student.id
            studentCheckBox.isChecked = student.isChecked

            // Handle checkbox state change
            studentCheckBox.setOnCheckedChangeListener { _, isChecked ->
                student.isChecked = isChecked
            }

            // Handle edit button click
            editButton.setOnClickListener {
                onEditClicked(student)
            }

            // Handle row click
            itemView.setOnClickListener {
                onStudentClicked(student)
            }
        }
    }
}
