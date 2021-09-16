package ru.boringowl.parapp.data.repository.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.boringowl.parapp.data.repository.PatternsRepository
import ru.boringowl.parapp.data.repository.room.dao.PatternsDAO
import ru.boringowl.parapp.domain.model.patterns.PatternInfo
import ru.boringowl.parapp.domain.model.patterns.PatternInfoDTO

class PatternsRepositoryImpl(application: Application) : PatternsRepository {
    private var patternsDAO: PatternsDAO
    private var allPatterns: LiveData<List<PatternInfoDTO>> = MutableLiveData()

    init {
        val db: MyDatabase = MyDatabase.getInstance(application)
        patternsDAO = db.patternsDAO()
        allPatterns = patternsDAO.getAllPatternInfo() ?: MutableLiveData()
    }

    override fun getAllPatternInfos(): LiveData<List<PatternInfo>?> {
        val patterns = arrayListOf<PatternInfo>()
        allPatterns.value!!.forEach {
            patterns.add(it.toPatternInfo())
        }
        return MutableLiveData(patterns)
    }

    override fun addPatternInfo(pattern: PatternInfo) {
        MyDatabase.databaseWriteExecutor.execute { patternsDAO.addPatternInfo(PatternInfoDTO(pattern)) }
    }

    override fun deletePatternInfo(pattern: PatternInfo) {
        MyDatabase.databaseWriteExecutor.execute { patternsDAO.deletePatternInfo(PatternInfoDTO(pattern)) }
    }

}