package com.venky.empinfo.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venky.empinfo.data.local.TokenManager
import com.venky.empinfo.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository,
    private val tokenManager: TokenManager
)
    : ViewModel() {
        private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
       val uiState: StateFlow<LoginUiState> = _uiState


    fun login(email: String, password: String) {

        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            try {
               val result = authRepository.login(email,password)

                tokenManager.saveToken(result.token)

                println("Login successful: ${result.name}")
                _uiState.value = LoginUiState.Success(result)
            }catch ( e :Exception){
                _uiState.value = LoginUiState.Error("Login failed: ${e.message}")
                println("Login failed: ${e.message}")

            }
        }
    }



}