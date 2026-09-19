package com.venky.empinfo.data.remote.api

import com.venky.empinfo.data.remote.dto.LoginRequest
import com.venky.empinfo.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface  AuthApi {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}