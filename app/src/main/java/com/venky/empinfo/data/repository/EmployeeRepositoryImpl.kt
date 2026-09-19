package com.venky.empinfo.data.repository

import com.venky.empinfo.data.mapper.toDomain
import com.venky.empinfo.data.remote.EmployeeApi
import com.venky.empinfo.domain.model.Employee
import com.venky.empinfo.domain.repository.EmployeeRepository
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(private val employeeApi: EmployeeApi)
    : EmployeeRepository {
    override suspend fun getEmployees(): List<Employee> {
       val response = employeeApi.getEmployees()

        return response.map {it.toDomain()}
    }


}