package com.venky.empinfo.data.repository

import com.venky.empinfo.data.mapper.toDomain
import com.venky.empinfo.data.remote.api.AuthApi
import com.venky.empinfo.data.remote.dto.LoginRequest
import com.venky.empinfo.domain.model.LoginResult
import com.venky.empinfo.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authApi: AuthApi)
    : AuthRepository {
    override suspend fun login(email: String, password: String): LoginResult {

        val response = authApi.login(LoginRequest(email, password))

        return response.toDomain()
    }


    }
