package com.venky.empinfo.domain.model

data class User(val id: Int,
                val name: String,
                val email: String,
                val image: String,
                val online: Boolean)