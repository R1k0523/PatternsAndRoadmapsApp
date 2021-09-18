package ru.boringowl.parapp.presentation.repository.room

import android.content.Context
import androidx.room.*
import ru.boringowl.parapp.presentation.repository.converters.DataTypeConverter
import ru.boringowl.parapp.presentation.repository.model.notes.NoteDTO
import ru.boringowl.parapp.presentation.repository.model.notes.RoadmapDTO
import ru.boringowl.parapp.presentation.repository.room.dao.PatternsDAO
import ru.boringowl.parapp.presentation.repository.model.patterns.PatternDTO
import ru.boringowl.parapp.presentation.repository.room.dao.NotesDAO
import ru.boringowl.parapp.presentation.repository.room.dao.RoadmapsDAO
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [PatternDTO::class, NoteDTO::class, RoadmapDTO::class], version = 1, exportSchema = false)
@TypeConverters(DataTypeConverter::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun patternsDAO(): PatternsDAO
    abstract fun notesDAO(): NotesDAO
    abstract fun roadmapsDAO(): RoadmapsDAO

    companion object {
        @Volatile
        private var instance: MyDatabase? = null

        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getInstance(context: Context): MyDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): MyDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MyDatabase::class.java,
                "parapp_db1"
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    }

}