package com.venky.empinfo.data.remote.dto

data class UserDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val image: String
)