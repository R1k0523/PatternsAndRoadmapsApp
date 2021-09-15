package ru.boringowl.parapp.data.repository

import android.app.Application
import ru.boringowl.parapp.data.repository.room.PatternsRepository

class Repository {
    var repository: RepositoryPatterns? = null

    fun init(application: Application) {
        if (repository == null) {
            repository = PatternsRepository(application)
        }
    }
}