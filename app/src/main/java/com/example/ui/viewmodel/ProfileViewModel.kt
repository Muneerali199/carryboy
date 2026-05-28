package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.User
import com.example.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProfileState(
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val user: User? = null,
    val error: String? = null,
    val isSaved: Boolean = false
)

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UserRepository()

    private val _profileState = MutableStateFlow(ProfileState())
    val profileState: StateFlow<ProfileState> = _profileState.asStateFlow()

    fun loadProfile(uid: String) {
        viewModelScope.launch {
            _profileState.value = _profileState.value.copy(isLoading = true)
            val result = repository.getUser(uid)
            result.onSuccess { user ->
                _profileState.value = ProfileState(user = user)
            }.onFailure { e ->
                _profileState.value = ProfileState(error = e.message)
            }
        }
    }

    fun saveProfile(user: User) {
        viewModelScope.launch {
            _profileState.value = _profileState.value.copy(isSaving = true, error = null)
            val updatedUser = user.copy(isProfileComplete = true)
            val result = repository.saveUser(updatedUser)
            result.onSuccess {
                _profileState.value = ProfileState(
                    user = updatedUser,
                    isSaved = true
                )
            }.onFailure { e ->
                _profileState.value = _profileState.value.copy(
                    isSaving = false,
                    error = e.message ?: "Failed to save profile"
                )
            }
        }
    }

    fun updateProfile(user: User) {
        viewModelScope.launch {
            _profileState.value = _profileState.value.copy(isSaving = true, error = null)
            val result = repository.updateUser(user)
            result.onSuccess {
                _profileState.value = ProfileState(
                    user = user,
                    isSaved = true
                )
            }.onFailure { e ->
                _profileState.value = _profileState.value.copy(
                    isSaving = false,
                    error = e.message ?: "Failed to update profile"
                )
            }
        }
    }

    fun clearSavedState() {
        _profileState.value = _profileState.value.copy(isSaved = false)
    }

    fun clearError() {
        _profileState.value = _profileState.value.copy(error = null)
    }
}
