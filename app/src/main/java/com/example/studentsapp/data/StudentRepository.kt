package com.example.studentsapp.data

import com.example.studentsapp.models.Student

object StudentRepository {
    private val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun getStudentById(id: String): Student? {
        return students.find { it.id == id }
    }

    fun deleteStudent(student: Student) {
        students.remove(student)
    }

    fun updateStudent(updatedStudent: Student, oldId: String) {
        // Find the student by the old ID
        val index = students.indexOfFirst { it.id == oldId }
        if (index != -1) {
            // Replace the student at the found index with the updated one
            students[index] = updatedStudent
        }
    }


    fun getAllStudents(): List<Student> {
        return students
    }

}


