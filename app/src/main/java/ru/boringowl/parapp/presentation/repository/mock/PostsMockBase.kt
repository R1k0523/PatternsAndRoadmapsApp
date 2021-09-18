package ru.boringowl.parapp.presentation.repository.mock

import ru.boringowl.parapp.domain.model.posts.notes.Note
import ru.boringowl.parapp.domain.model.posts.notes.NoteSection
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.domain.model.posts.roadmaps.RoadmapNode
import java.text.SimpleDateFormat
import java.util.*

class PostsMockBase {
    var notes = listOf(
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
    val roadmaps = listOf(
        Roadmap(
            null,
            "Дорожка 3",
            SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.ENGLISH).format(Date()),
            listOf("Категория 11","Категория 12","Категория 13"),
            "Описание поста. Описание поста. Описание поста. Описание поста Наверное",
            RoadmapNode(
                "Начать",
                "Встать с кровати",
                listOf(
                    RoadmapNode(
                        "Глянуть вк",
                        "Еще и курс алго можно",
                        listOf()
                    ),
                    RoadmapNode(
                        "Потянуться",
                        "Встать с кровати",
                        listOf(
                            RoadmapNode(
                                "Сделать зарядку",
                                "15 минут никому не помешают",
                                listOf(
                                    RoadmapNode(
                                        "Поесть",
                                        "Что ты сегодня выберешь",
                                        listOf(
                                            RoadmapNode(
                                                "Багет",
                                                "123",
                                                listOf(),
                                            ),
                                            RoadmapNode(
                                                "Яичница",
                                                "456",
                                                listOf(),
                                            ),
                                            RoadmapNode(
                                                "Йогурт",
                                                "789",
                                                listOf(),
                                            ),
                                            RoadmapNode(
                                                "Умыться",
                                                "Чисти чисти",
                                                listOf(

                                                    RoadmapNode(
                                                        "Одеться",
                                                        "Желательно потеплее",
                                                        listOf(
                                                            RoadmapNode(
                                                            "Погладить одежду",
                                                            "А зачем?",
                                                                listOf(),
                                                            ),
                                                            RoadmapNode(
                                                                "Готов к выходу",
                                                                "А может прогулять?",
                                                                listOf(),
                                                                true
                                                            ),
                                                        ),
                                                        true,
                                                    ),
                                                ),
                                                true
                                            ),

                                        ),
                                        true
                                    ),
                                ),
                                true
                            ),
                        ),
                        true
                    ),
                    RoadmapNode(
                        "Накрытья одеялом",
                        "И еще поспать",
                        listOf()
                    ),
                ),
                true
            )

        )
    )
}