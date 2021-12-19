package ru.boringowl.parapp.presentation.repository.network.parapp

import ru.boringowl.parapp.domain.model.patterns.Pattern
import ru.boringowl.parapp.presentation.repository.network.BaseService
import ru.boringowl.parapp.presentation.repository.network.MyResult
import ru.boringowl.parapp.presentation.repository.network.parapp.response.GenericResponse
import java.util.*

class PatternsService(private val api: ParappDataAPI) : BaseService() {
    suspend fun createPattern(token: String,topic: Pattern) : Pattern {
        return when(val result = createCall { api.createPattern(token, topic) }){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }
    }
    suspend fun updatePattern(token: String, topic: Pattern) : Pattern {
        return when(val result = createCall { api.createPattern(token, topic) }){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }
    }
    suspend fun deletePattern(token: String, id: UUID) : GenericResponse {
        return when(val result = createCall { api.deletePattern(token, id)}){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }

    }
    suspend fun getPattern(id: UUID) : Pattern {
        return when(val result = createCall { api.getPattern(id) }){
            is MyResult.Success -> result.data
            is MyResult.Error -> throw result.error
        }

    }
    suspend fun getAllPatterns() : List<Pattern> {
        return when(val result = createCall { api.getAllPatterns() }){
            is MyResult.Success -> result.data.items
            is MyResult.Error -> throw result.error
        }
    }
}