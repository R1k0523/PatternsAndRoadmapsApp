package ru.boringowl.parapp.presentation.repository.room

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.boringowl.parapp.presentation.repository.PatternsRepository
import ru.boringowl.parapp.presentation.repository.room.dao.PatternsDAO
import ru.boringowl.parapp.domain.model.patterns.PatternInfo
import ru.boringowl.parapp.presentation.repository.model.patterns.PatternInfoDTO

class PatternsRepositoryImpl(application: Application) : PatternsRepository {
    private var patternsDAO: PatternsDAO
    private var allPatterns: LiveData<List<PatternInfoDTO>>

    init {
        val db: MyDatabase = MyDatabase.getInstance(application)
        patternsDAO = db.patternsDAO()
        allPatterns =  patternsDAO.getAllPatternInfo()
    }

    override fun <T : PatternInfo> getAllPatternInfos(): LiveData<List<T>> {
        return allPatterns as LiveData<List<T>>
    }

    override fun <T : PatternInfo> addPatternInfo(pattern: T) {
        MyDatabase.databaseWriteExecutor.execute { patternsDAO.addPatternInfo(PatternInfoDTO(pattern)) }
    }

    override fun <T : PatternInfo> getPatternInfo(patternId: Int): T {
        return patternsDAO.getPatternInfo(patternId) as T
    }

    override fun <T : PatternInfo> deletePatternInfo(pattern: T) {
        MyDatabase.databaseWriteExecutor.execute { patternsDAO.deletePatternInfo(pattern as PatternInfoDTO) }
    }

}