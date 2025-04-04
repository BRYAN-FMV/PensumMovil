package com.devproy.pensummovil.Model

data class UserResponse (
    val userId: String,
    val password: String
)

data class LoginResponse(
    val message: String,
    val userId: String,
    val RoleId: String?,
    val token: String
)