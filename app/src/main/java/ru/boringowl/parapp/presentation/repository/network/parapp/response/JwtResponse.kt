package ru.boringowl.parapp.presentation.repository.network.parapp.response

import java.util.*

class JwtResponse(
    var accessToken: String,
    var refreshToken: String,
    var id: UUID,
    var username: String,
    val role: String
) : GenericResponse() { val tokenType = "Bearer"
    fun token() = "$tokenType $accessToken"
}