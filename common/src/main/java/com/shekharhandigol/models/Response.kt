package com.shekharhandigol.models

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val status: Int = -1, val message: String, val cause: Exception? = null) :
        Resource<T>()
}