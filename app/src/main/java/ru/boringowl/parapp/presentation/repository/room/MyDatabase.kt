package ru.boringowl.parapp.presentation.repository.room

import android.content.Context
import androidx.room.*
import ru.boringowl.parapp.presentation.repository.converters.DataTypeConverter
import ru.boringowl.parapp.presentation.repository.model.notes.NoteDTO
import ru.boringowl.parapp.presentation.repository.model.notes.RoadmapDTO
import ru.boringowl.parapp.presentation.repository.model.notes.TopicDTO
import ru.boringowl.parapp.presentation.repository.model.patterns.PatternDTO
import ru.boringowl.parapp.presentation.repository.model.user.UserDTO
import ru.boringowl.parapp.presentation.repository.room.dao.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [
    PatternDTO::class,
    NoteDTO::class,
    RoadmapDTO::class,
    UserDTO::class,
    TopicDTO::class],
    version = 1, exportSchema = false)
@TypeConverters(DataTypeConverter::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun patternsDAO(): PatternsDAO
    abstract fun notesDAO(): NotesDAO
    abstract fun roadmapsDAO(): RoadmapsDAO
    abstract fun usersDAO(): UserDAO
    abstract fun topicDAO(): TopicDAO

    companion object {

        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
    }

}