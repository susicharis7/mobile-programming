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
    private val _remainingTasks = MutableStateFlow<List<TaskWithSubject>>(emptyList())
    val remainingTasks: StateFlow<List<TaskWithSubject>> = _remainingTasks

    private val _completedTasks = MutableStateFlow<List<TaskWithSubject>>(emptyList())
    val completedTasks: StateFlow<List<TaskWithSubject>> = _completedTasks

    private val _subjectTaskCounts = MutableStateFlow<Map<Long, SubjectTaskCount>>(emptyMap())
    val subjectTaskCounts: StateFlow<Map<Long, SubjectTaskCount>> = _subjectTaskCounts

    data class SubjectTaskCount(
        val completed: Int,
        val remaining: Int
    )

    private val _completedTaskCount = MutableStateFlow<Int?>(null)
    val completedTaskCount: StateFlow<Int?> = _completedTaskCount

    private val _totalTaskCount = MutableStateFlow<Int?>(null)
    val totalTaskCount: StateFlow<Int?> = _totalTaskCount

    private val _remainingTaskCount = MutableStateFlow<Int?>(null)
    val remainingTaskCount: StateFlow<Int?> = _remainingTaskCount

    fun loadTasks(userId: Long) {
        viewModelScope.launch {
            val remainingList = taskRepo.getTasksWithSubjectByUserIdAndIsCompleted(userId, false)
            _remainingTasks.value = remainingList

            val completedList = taskRepo.getTasksWithSubjectByUserIdAndIsCompleted(userId, true)
            _completedTasks.value = completedList
        }
    }
    fun loadSubjectTaskCounts(userId: Long, subjectIds: List<Long>) {
//        println("DEBUG: Loading counts for user $userId, subjects $subjectIds")
        viewModelScope.launch {
            val counts = mutableMapOf<Long, SubjectTaskCount>()

            subjectIds.forEach() { subjectId ->
                val completed = taskRepo.getTasksCountByUserIdAndSubjectAndIsCompleted(userId, subjectId, true)
                val remaining = taskRepo.getTasksCountByUserIdAndSubjectAndIsCompleted(userId, subjectId, false)
                counts[subjectId] = SubjectTaskCount(completed, remaining)
            }
            _subjectTaskCounts.value = counts
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