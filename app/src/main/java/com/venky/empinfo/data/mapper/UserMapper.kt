package com.venky.empinfo.data.mapper

import com.venky.empinfo.data.remote.dto.UserDto
import com.venky.empinfo.domain.model.User

fun UserDto.toUser(): User {
    return User(
        id = id,
        name = "$firstName $lastName",
        email = email,
        image = image,
        online = true
    )
}
