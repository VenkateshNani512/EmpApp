package com.venky.empinfo.presentation.login

import com.venky.empinfo.data.remote.dto.LoginResponse
import com.venky.empinfo.domain.model.LoginResult

sealed class LoginUiState {
    data object Idle : LoginUiState()

   data object Loading:LoginUiState()

    data class Success(val user: LoginResult): LoginUiState()
    data class Error(val message:String): LoginUiState()
}