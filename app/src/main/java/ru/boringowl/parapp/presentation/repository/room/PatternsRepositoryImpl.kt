package ru.boringowl.parapp.presentation.repository.room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.presentation.repository.PatternsRepository
import ru.boringowl.parapp.presentation.repository.room.dao.PatternsDAO
import ru.boringowl.parapp.domain.model.patterns.Pattern
import ru.boringowl.parapp.presentation.repository.model.patterns.PatternDTO
import ru.boringowl.parapp.presentation.repository.network.parapp.PatternsService
import ru.boringowl.parapp.presentation.utils.PrefsUtils
import java.util.*

class PatternsRepositoryImpl : PatternsRepository {
    private var patternsDAO: PatternsDAO
    private var allPatterns: LiveData<List<Pattern>>
    private val patternService: PatternsService by inject(PatternsService::class.java)
    private val prefs by inject(PrefsUtils::class.java)
    init {
        val db: MyDatabase by inject(MyDatabase::class.java)
        patternsDAO = db.patternsDAO()
        allPatterns = liveData {
            val disposable = emitSource(
                patternsDAO.getAllPatterns() as LiveData<List<Pattern>>
            )
            try {
                val patterns = patternService.getAllPatterns()
                disposable.dispose()
                patternsDAO.deleteAllPatterns()
                patterns.forEach {
                    patternsDAO.addPattern(PatternDTO(it))
                }
                emitSource(
                    patternsDAO.getAllPatterns() as LiveData<List<Pattern>>
                )
            } catch (exception: Exception) {
                Log.d("Pattern", exception.localizedMessage)
                emitSource(
                    patternsDAO.getAllPatterns() as LiveData<List<Pattern>>
                )
            }
        }
    }

    override fun <T : Pattern> getAllPatterns(): LiveData<List<T>> {
        return allPatterns as LiveData<List<T>>
    }

    override suspend fun <T : Pattern> addPattern(pattern: T) {
        try {
            val savedPattern = patternService.createPattern(prefs.getToken()!!, pattern)
            patternsDAO.addPattern(PatternDTO(savedPattern))
        } catch (e: Exception) {}
    }

    override fun <T : Pattern> getPattern(patternId: UUID): LiveData<T?> {
        val pattern: LiveData<Pattern?> = liveData {
            val disposable = emitSource(
                patternsDAO.getPattern(patternId) as LiveData<Pattern?>
            )
            try {
                val pattern = patternService.getPattern(patternId)
                disposable.dispose()
                patternsDAO.deletePattern(pattern.patternId!!)
                patternsDAO.addPattern(PatternDTO(pattern))
                emitSource(
                    patternsDAO.getPattern(patternId) as LiveData<Pattern?>
                )
            } catch (exception: Exception) {
                emitSource(
                    patternsDAO.getPattern(patternId) as LiveData<Pattern?>
                )
            }
        }
        return pattern as LiveData<T?>
    }

    override suspend fun <T : Pattern> deletePattern(pattern: T) {
        patternService.deletePattern(prefs.getToken()!!, pattern.patternId!!)
        patternsDAO.deletePattern(pattern as PatternDTO)
    }

}