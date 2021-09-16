package ru.boringowl.parapp.data.repository

import android.app.Application
import ru.boringowl.parapp.data.repository.mock.MockBase
import ru.boringowl.parapp.data.repository.room.PatternsRepository

class Repository {
    companion object {
        var repository: RepositoryPatterns? = null
            get() : RepositoryPatterns? {
                if (field == null) {
                    repository = MockBase()
                }
                return field
            }

        fun init(application: Application) {
            if (repository == null) {
                repository = PatternsRepository(application)
            }
        }
    }

}