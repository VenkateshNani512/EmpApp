package com.venky.empinfo.domain.repository

import com.venky.empinfo.domain.model.Employee

interface EmployeeRepository {
    suspend fun getEmployees():List<Employee>
}