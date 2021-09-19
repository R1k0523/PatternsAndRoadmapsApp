package ru.boringowl.parapp.presentation.repository.mock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.boringowl.parapp.domain.model.posts.Post
import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.notes.NoteSection
import ru.boringowl.parapp.presentation.repository.NotesRepository
import java.text.SimpleDateFormat
import java.util.*

class NotesMockBase : NotesRepository {
    var data: MutableLiveData<List<Note>>
    var list: List<Note>

    init {
        var notes = listOf(
            Note(
                0,
                "Название",
                SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.ENGLISH).format(Date()),
                listOf("Категория 1","Категория 2","Категория 3"),
                "Описание поста. Описание поста. Описание поста. Описание поста",
                listOf(
                    NoteSection(
                        "Описание секции №1 Ну тут будет какой то текст, который говорит о том, что будет в recyclerView ниже",
                        listOf("Купить чебупели", "Скастовать еще 24 часа в сутки", "Сделать практику по мобилкам", "Хочу есть")
                    )
                )
            ),
            Note(
                1,
                "Название 2",
                SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.ENGLISH).format(Date()),
                listOf("Категория 10","Категория 20","Категория 30"),
                "Описание поста. Описание поста. Описание поста. Описание поста",
                listOf(
                    NoteSection(
                        "Описание секции №1 Ну тут будет какой то текст, который говорит о том, что будет в recyclerView ниже",
                        listOf("Купить чебупели", "Скастовать еще 24 часа в сутки", "Сделать практику по мобилкам", "Хочу есть")
                    ),

                    NoteSection(
                        "Описание секции №2 Ну тут тоже будет какой то текст, который говорит о том, что будет в recyclerView ниже",
                        listOf("Купить чебупели", "Хочу есть")
                    )
                )
            ),
            Note(
                2,
                "Название 3",
                SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.ENGLISH).format(Date()),
                listOf("Категория 11","Категория 12","Категория 13"),
                "Описание поста. Описание поста. Описание поста. Описание поста Наверное",
                listOf(
                    NoteSection(
                        "Описание секции №2 Ну тут будет какой то текст, который говорит о том, что будет в recyclerView ниже",
                        listOf("Скастовать еще 24 часа в сутки", "Сделать практику по мобилкам",)
                    )
                )
            )
        )
        list = notes
        data = MutableLiveData(list)
    }

    override fun <T : Note> getAllNotes(): LiveData<List<T>> {
        return data as LiveData<List<T>>
    }

    override fun <T : Note> addNote(note: T) {
        list = list + note
        data.value = list
    }

    override fun <T : Note> getNote(noteId: Int): T {
        list.forEach {
            if (it.id == noteId)
                return it as T
        }
        return Note(0, "", "", listOf(""), "", listOf()) as T
    }

    override fun <T : Note> deleteNote(note: T) {
        list = list - note
        data.value = list
    }
}


