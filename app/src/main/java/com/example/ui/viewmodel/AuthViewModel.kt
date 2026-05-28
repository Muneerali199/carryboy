package com.example.ui.viewmodel

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.User
import com.example.data.model.UserRole
import com.example.data.repository.UserRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthState(
    val isLoading: Boolean = true,
    val isLoggedIn: Boolean = false,
    val user: User? = null,
    val error: String? = null
)

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UserRepository()

    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getApplication<Application>().getString(
                getApplication<Application>().resources.getIdentifier(
                    "default_web_client_id", "string", getApplication<Application>().packageName
                )
            ))
            .requestEmail()
            .build()
        GoogleSignIn.getClient(getApplication(), gso)
    }

    init {
        checkAuthState()
    }

    fun getGoogleSignInIntent(): Intent {
        return googleSignInClient.signInIntent
    }

    fun signInWithGoogle(idToken: String, role: UserRole? = null) {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true, error = null)
            val result = repository.signInWithGoogle(idToken, role)
            result.onSuccess { user ->
                _authState.value = AuthState(
                    isLoading = false,
                    isLoggedIn = true,
                    user = user
                )
            }.onFailure { e ->
                _authState.value = _authState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Google sign-in failed"
                )
            }
        }
    }

    private fun checkAuthState() {
        viewModelScope.launch {
            _authState.value = AuthState(isLoading = true)
            if (repository.isLoggedIn) {
                val uid = repository.currentFirebaseUser?.uid ?: ""
                if (uid.isNotEmpty()) {
                    val result = repository.getUser(uid)
                    result.onSuccess { user ->
                        _authState.value = AuthState(
                            isLoading = false,
                            isLoggedIn = true,
                            user = user
                        )
                    }.onFailure {
                        _authState.value = AuthState(isLoading = false)
                    }
                } else {
                    _authState.value = AuthState(isLoading = false)
                }
            } else {
                _authState.value = AuthState(isLoading = false)
            }
        }
    }

    fun signUp(email: String, password: String, role: UserRole) {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true, error = null)
            val result = repository.signUp(email, password, role)
            result.onSuccess { user ->
                _authState.value = AuthState(
                    isLoading = false,
                    isLoggedIn = true,
                    user = user
                )
            }.onFailure { e ->
                _authState.value = _authState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Sign up failed"
                )
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true, error = null)
            val result = repository.login(email, password)
            result.onSuccess { user ->
                _authState.value = AuthState(
                    isLoading = false,
                    isLoggedIn = true,
                    user = user
                )
            }.onFailure { e ->
                _authState.value = _authState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Login failed"
                )
            }
        }
    }

    fun logout() {
        googleSignInClient.signOut()
        repository.logout()
        _authState.value = AuthState(isLoading = false)
    }

    fun clearError() {
        _authState.value = _authState.value.copy(error = null)
    }
}
