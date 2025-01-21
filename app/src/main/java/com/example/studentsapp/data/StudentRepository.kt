package com.example.studentsapp.data

import com.example.studentsapp.models.Student

object StudentRepository {
    val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun deleteStudent(student: Student) {
        students.remove(student)
    }

    fun updateStudent(updatedStudent: Student) {
        val index = students.indexOfFirst { it.id == updatedStudent.id }
        if (index != -1) {
            students[index] = updatedStudent
        }
    }
}
