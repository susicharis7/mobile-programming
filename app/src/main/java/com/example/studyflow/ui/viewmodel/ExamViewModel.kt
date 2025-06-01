package com.example.studyflow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyflow.model.Exam
import com.example.studyflow.model.ExamWithSubject
import com.example.studyflow.repository.ExamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ExamViewModel @Inject constructor(
    private val examRepo: ExamRepository
) : ViewModel() {
    private val _exams = MutableStateFlow<List<ExamWithSubject>>(emptyList())
    val exams: StateFlow<List<ExamWithSubject>> = _exams

    private val _daysLeft = MutableStateFlow<Int?>(null)
    val daysLeft: StateFlow<Int?> = _daysLeft

    fun loadExams(userId: Long) {
        viewModelScope.launch {
            val list = examRepo.getExamsWithSubjectByUserId(userId)
            _exams.value = list
        }
    }
    fun getDaysLeft(deadline: Date): String {
        val currentDate = Date()
        val difference = deadline.time - currentDate.time
        val daysLeft = (difference / (1000 * 60 * 60 * 24)).toInt()
        return daysLeft.toString()
    }
}