package com.venky.empinfo.data.remote.dto
data  class LoginResponse(val id:Int,
                          val name: String,
                          val email :String,
                          val password: String,
                          val token: String)

