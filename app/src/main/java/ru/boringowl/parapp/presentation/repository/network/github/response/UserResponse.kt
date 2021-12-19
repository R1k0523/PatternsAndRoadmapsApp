package ru.boringowl.parapp.presentation.repository.network.github.response

data class UserResponse(
    val bio: String = "",
    val login: String = "",
    val company: String = "",
    val email: String = "",
    val url: String = "",
    val avatarUrl: String = "",
    val name: String = "",
    val location: String = "",
    val blog: String = ""
)
