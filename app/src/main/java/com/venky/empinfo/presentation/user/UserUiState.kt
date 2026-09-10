package com.venky.empinfo.presentation.user

import com.venky.empinfo.domain.model.User

sealed class UserUiState {
    object Loading : UserUiState()
    data class Success(val users: List<User>) : UserUiState()
    data class Error(val message: String) : UserUiState()

}