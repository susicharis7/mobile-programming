package com.example.studyflow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyflow.model.Subject
import com.example.studyflow.repository.SubjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(
    private val subjectRepo: SubjectRepository,
) : ViewModel() {
    private val _subjects = MutableStateFlow<List<Subject>>(emptyList())
    val subjects: StateFlow<List<Subject>> = _subjects

    fun loadSubjects(userId: Long) {
        viewModelScope.launch {
            val list = subjectRepo.getSubjectByUserId(userId)
            _subjects.value = list
        }
    }
    fun insertSubject(subject: Subject) {
        viewModelScope.launch {
            subjectRepo.insert(subject)
        }
    }
    fun updateSubject(subject: Subject) {
        viewModelScope.launch {
            subjectRepo.update(subject)
        }
    }
    fun deleteSubject(subject: Subject) {
        viewModelScope.launch {
            subjectRepo.delete(subject)
        }
    }
}