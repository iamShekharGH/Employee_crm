package com.shekharhandigol.models

import kotlinx.serialization.Serializable

interface Request
interface Response

@Serializable
data class LoginRequest(
    val username: String,
    val password: String
) : Request

@Serializable
data class LoginResponse(
    val status: Int,
    val data: UserInformation
) : Response

@Serializable
data class ErrorResponse(
    val status: Int,
    val message: String
) : Response
