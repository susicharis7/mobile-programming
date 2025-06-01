package com.example.studyflow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyflow.model.Task
import com.example.studyflow.model.TaskWithSubject
import com.example.studyflow.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskRepo: TaskRepository,
) : ViewModel() {
    private val _tasks = MutableStateFlow<List<TaskWithSubject>>(emptyList())
    val tasks: StateFlow<List<TaskWithSubject>> = _tasks

    private val _completedTaskCount = MutableStateFlow<Int?>(null)
    val completedTaskCount: StateFlow<Int?> = _completedTaskCount

    private val _totalTaskCount = MutableStateFlow<Int?>(null)
    val totalTaskCount: StateFlow<Int?> = _totalTaskCount

    private val _remainingTaskCount = MutableStateFlow<Int?>(null)
    val remainingTaskCount: StateFlow<Int?> = _remainingTaskCount

    fun loadTasks(userId: Long) {
        viewModelScope.launch {
            val list = taskRepo.getTasksWithSubjectByUserId(userId)
            _tasks.value = list
        }
    }
    fun loadTaskCounts(userId: Long) {
        viewModelScope.launch {
            val completedCount = taskRepo.getCompletedTaskCountByUserId(userId)
            _completedTaskCount.value = completedCount

            val totalCount = taskRepo.getTotalTaskCountByUserId(userId)
            _totalTaskCount.value = totalCount

            val remainingCount = totalCount - completedCount
            _remainingTaskCount.value = remainingCount
        }
    }
}