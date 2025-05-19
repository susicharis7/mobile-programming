package com.example.studyflow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyflow.model.Exam
import com.example.studyflow.repository.ExamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamViewModel @Inject constructor(
    private val examRepo: ExamRepository
) : ViewModel() {
    private val _exams = MutableStateFlow<List<Exam>>(emptyList())
    val exams: StateFlow<List<Exam>> = _exams

    fun loadExams(userId: Int) {
        viewModelScope.launch {
            val list = examRepo.getExamsByUserId(userId)
            _exams.value = list
        }
    }
}