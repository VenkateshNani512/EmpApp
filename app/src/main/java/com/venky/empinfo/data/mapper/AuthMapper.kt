package com.venky.empinfo.data.mapper

import com.venky.empinfo.data.remote.dto.LoginResponse
import com.venky.empinfo.domain.model.LoginResult

fun LoginResponse.toDomain(): LoginResult {
    return LoginResult(id = id,
        name = name,
        email = email,
        token = token)
}