package com.venky.empinfo.domain.model

data class LoginResult(val id: Int,
                       val name :String,
                       val email:String,
                       val token:String)
