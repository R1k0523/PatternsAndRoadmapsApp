package ru.boringowl.parapp.presentation.repository.network.parapp.response

class TokenRefreshResponse(
    var accessToken: String,
    var refreshToken: String) {
    var tokenType = "Bearer"
}