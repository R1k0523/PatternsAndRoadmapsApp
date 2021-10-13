package ru.boringowl.parapp.domain.model.news

sealed class MyResult<out T: Any> {
    data class Success<out T: Any>(val data: T) : MyResult<T>()
    data class Error(val error: Exception) : MyResult<Nothing>()
}