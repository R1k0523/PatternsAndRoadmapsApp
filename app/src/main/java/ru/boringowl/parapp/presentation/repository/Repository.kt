package ru.boringowl.parapp.presentation.repository

import android.app.Application
import ru.boringowl.parapp.presentation.repository.mock.MockBase
import ru.boringowl.parapp.presentation.repository.room.PatternsRepositoryImpl

class Repository {
    companion object {
        var repository: PatternsRepository? = null
            get() : PatternsRepository? {
                if (field == null) {
                    repository = MockBase()
                }
                return field
            }

        fun init(application: Application) {
            if (repository == null) {
                repository = PatternsRepositoryImpl(application)
            }
        }
    }

}