package ru.boringowl.parapp.presentation.repository.network.parapp.response

import java.util.*

class ErrorMessage(
    val statusCode: Int,
    val timestamp: Date,
    val message: String,
    val description: String
)