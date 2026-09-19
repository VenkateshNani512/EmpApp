package com.venky.empinfo.data.remote

import com.venky.empinfo.data.remote.dto.EmployeeResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface EmployeeApi {

    @GET("employee")
    suspend fun getEmployees(): List<EmployeeResponse>
}