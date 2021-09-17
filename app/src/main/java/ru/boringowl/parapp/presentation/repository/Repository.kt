package ru.boringowl.parapp.presentation.repository

import android.app.Application
import android.util.Log
import ru.boringowl.parapp.presentation.repository.mock.MockBase
import ru.boringowl.parapp.presentation.repository.room.PatternsRepositoryImpl

class Repository {
    companion object {
        var repository: PatternsRepository? = null

        fun initRepository() {
            if (repository == null) {
                Log.d("rep", "mock")
                repository = MockBase()
            }
        }
        fun initRepository(application: Application) {
            if (repository == null) {
                Log.d("rep", "impl")
                repository = PatternsRepositoryImpl(application)
            }
        }
    }

}