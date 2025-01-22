package com.example.studentsapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.activities.StudentDetailsActivity
import com.example.studentsapp.models.Student

class StudentsAdapter(
    private var students: MutableList<Student>,
    private val onEditClicked: (Student) -> Unit
) : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount(): Int = students.size

    fun updateData(newStudents: List<Student>) {
        students.clear()
        students.addAll(newStudents)
        notifyDataSetChanged()
    }

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studentImageView: ImageView = itemView.findViewById(R.id.studentImageView)
        private val studentNameTextView: TextView = itemView.findViewById(R.id.studentNameTextView)
        private val studentIdTextView: TextView = itemView.findViewById(R.id.studentIdTextView)
        private val studentCheckBox: CheckBox = itemView.findViewById(R.id.statusCheckBox)
        private val editButton: ImageButton = itemView.findViewById(R.id.editButton)

        fun bind(student: Student) {
            // Set default image or student's image if available
            studentImageView.setImageResource(R.drawable.default_student_image)
            studentNameTextView.text = student.name
            studentIdTextView.text = student.id
            studentCheckBox.isChecked = student.isChecked

            // Handle checkbox click
            studentCheckBox.setOnCheckedChangeListener { _, isChecked ->
                student.isChecked = isChecked
            }

            // Handle edit button click
            editButton.setOnClickListener {
                onEditClicked(student)
            }

            // Handle item click for details
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, StudentDetailsActivity::class.java)
                intent.putExtra("student", student)
                context.startActivity(intent)
            }
        }
    }
}
