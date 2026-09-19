package com.venky.empinfo.presentation.employee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venky.empinfo.domain.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class EmployeeViewModel @Inject constructor(private val employeeRepository: EmployeeRepository)
    : ViewModel(){

        private val _UiState = MutableStateFlow<EmployeeUiState>(EmployeeUiState.Idle)
        val uiState:StateFlow<EmployeeUiState> = _UiState

    fun getEmployee(){
        viewModelScope.launch {
            _UiState.value = EmployeeUiState.Loading
            try {
               val employees = employeeRepository.getEmployees()
               _UiState.value = EmployeeUiState.Success(employees)

            }catch (e:Exception){
                _UiState.value = EmployeeUiState.Error(e.message ?: "Unknown error")
            }
            }

        }

}