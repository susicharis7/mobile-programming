package com.example.studyflow.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyflow.model.User
import com.example.studyflow.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepo: UserRepository,
) : ViewModel() {
    private val _registrationSuccess = MutableStateFlow(false)
    val registrationSuccess: StateFlow<Boolean> = _registrationSuccess

    private val _loginStatus = MutableStateFlow<Boolean?>(null)
    val loginStatus: StateFlow<Boolean?> = _loginStatus

    private val _loggedUser = MutableStateFlow<User?>(null)
    val loggedUser: StateFlow<User?> = _loggedUser

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                userRepo.insert(User(username=username, password=password, email=email))

                // added middle step so when a user registers they get immediately logged in
                val authenticatedUser = userRepo.getUserByEmailAndPassword(email, password)

                _registrationSuccess.value = true
                _loggedUser.value = authenticatedUser
            } catch (e: Exception) {
                _error.value = "Registration failed: ${e.message}"
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = userRepo.getUserByEmailAndPassword(email=email, password=password)
            _loginStatus.value = user != null
            if (_loginStatus.value == true) {
                _loggedUser.value = user
                Log.d("logged", "_loggedUser $(loggedUser.value)")
            }
        }
    }
}