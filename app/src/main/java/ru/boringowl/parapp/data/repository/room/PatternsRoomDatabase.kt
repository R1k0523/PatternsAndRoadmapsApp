package ru.boringowl.parapp.data.repository.room

import android.app.Application
import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import ru.boringowl.parapp.data.repository.room.dao.PatternsDAO
import ru.boringowl.parapp.domain.model.patterns.PatternInfoDTO
import java.util.concurrent.Executors

@Database(entities = [PatternInfoDTO::class /* другие таблички*/], version = 1, exportSchema = false)
abstract class PatternsRoomDatabase : RoomDatabase() {
    abstract fun patternsDAO(): PatternsDAO?

    companion object {
        @Volatile
        private var instance: PatternsRoomDatabase? = null

        private val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getInstance(context: Context): PatternsRoomDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): PatternsRoomDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                PatternsRoomDatabase::class.java,
                "parapp_db"
            )
                .allowMainThreadQueries()
                .build()
        }
    }

}