package ru.boringowl.parapp.data.repository.room

import android.content.Context
import androidx.room.*
import ru.boringowl.parapp.data.converters.DataTypeConverter
import ru.boringowl.parapp.data.repository.room.dao.PatternsDAO
import ru.boringowl.parapp.domain.model.patterns.PatternInfoDTO
import java.util.concurrent.Executors

@Database(entities = [PatternInfoDTO::class /* другие таблички*/], version = 1, exportSchema = false)
@TypeConverters(DataTypeConverter::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun patternsDAO(): PatternsDAO

    companion object {
        @Volatile
        private var instance: MyDatabase? = null

        private val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getInstance(context: Context): MyDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): MyDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MyDatabase::class.java,
                "parapp_db"
            )
                .allowMainThreadQueries()
                .build()
        }
    }

}