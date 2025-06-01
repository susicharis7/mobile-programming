package com.example.studyflow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyflow.model.TimerType
import com.example.studyflow.model.Timersession
import com.example.studyflow.repository.TimersessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val timersessionRepo: TimersessionRepository,
) : ViewModel() {
    private val _timerStats = MutableStateFlow<List<Timersession>>(emptyList())
    val timerStats: StateFlow<List<Timersession>> = _timerStats

    fun loadTimerStats(userId: Long, timerType: TimerType) {
        viewModelScope.launch {
            val list = timersessionRepo.getTimerSessionsByUserIdAndTimerType(userId, timerType)
            _timerStats.value = list
        }
    }
}