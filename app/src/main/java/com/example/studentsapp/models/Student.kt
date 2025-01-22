package com.example.studentsapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    var name: String,
    var id: String,
    var phone: String,
    var address: String,
    val imageUri: String = "",
    var isChecked: Boolean = false
) : Parcelable
