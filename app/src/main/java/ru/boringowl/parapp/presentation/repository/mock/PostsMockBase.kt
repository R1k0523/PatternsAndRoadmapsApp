package ru.boringowl.parapp.presentation.repository.mock

import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.notes.NoteSection
import java.text.SimpleDateFormat
import java.util.*

class PostsMockBase {
    var list = listOf(
        Note(
            null,
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
            null,
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
            null,
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
}