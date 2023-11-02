package com.cmps312.viewmodeltutorial

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyViewModel : ViewModel() {

    var myStateFlow by mutableStateOf(0)
        private set

    var stateFlow = StateFlow(listOf<Student>())


    private val _students by lazy { MutableStateFlow(StudentRepo.students) }
    val students = _students.asStateFlow()

    private var _selectedStudent = mutableStateOf(_students.value[0])
    val selectedStudent: State<Student> = _selectedStudent


    fun showStudentDetail(it: Student) {
        _selectedStudent.value = it
    }
}