package com.venky.empinfo.data.mapper

import com.venky.empinfo.data.remote.dto.EmployeeResponse
import com.venky.empinfo.domain.model.Employee

fun  EmployeeResponse.toDomain(): Employee {

    return Employee(id = id,
        name = name,
        email = email,
        skills = skills)
}