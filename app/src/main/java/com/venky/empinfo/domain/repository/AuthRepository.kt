package com.venky.empinfo.domain.repository

import com.venky.empinfo.domain.model.LoginResult

interface AuthRepository {

    suspend fun login(email:String,
                password: String): LoginResult
}