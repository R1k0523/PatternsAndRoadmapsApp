package ru.boringowl.parapp.data.repository.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.boringowl.parapp.data.repository.RepositoryPatterns
import ru.boringowl.parapp.data.repository.room.dao.PatternsDAO
import ru.boringowl.parapp.domain.model.patterns.PatternInfo
import ru.boringowl.parapp.domain.model.patterns.PatternInfoDTO

class PatternsRepository(application: Application) : RepositoryPatterns {
    private var patternsDAO: PatternsDAO? = null
    private var allPatterns: LiveData<List<PatternInfoDTO>> = MutableLiveData()

    init {
        val db: PatternsRoomDatabase = PatternsRoomDatabase.getInstance(application)
        patternsDAO = db.patternsDAO()
        allPatterns = patternsDAO?.getAllPatternInfo() ?: MutableLiveData()
    }

    override fun getAllPatternInfo(): LiveData<List<PatternInfo>?>? {
        TODO("Not yet implemented")
    }

    override fun addPatternInfo(party: PatternInfo) {
        TODO("Not yet implemented")
    }

    override fun deletePatternInfo(party: PatternInfo) {
        TODO("Not yet implemented")
    }

}