package com.venky.empinfo.presentation.employee

import com.venky.empinfo.domain.model.Employee

sealed class EmployeeUiState {
   data object Idle: EmployeeUiState()
    data object Loading: EmployeeUiState()
    data class Success(val employees:List<Employee>): EmployeeUiState()
    data class Error(val message:String): EmployeeUiState()


}