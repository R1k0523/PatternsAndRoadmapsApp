package ru.boringowl.parapp.presentation.repository.mock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.boringowl.parapp.presentation.repository.PatternsRepository
import ru.boringowl.parapp.domain.model.patterns.PatternFeature
import ru.boringowl.parapp.domain.model.patterns.Pattern
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.domain.model.posts.roadmaps.RoadmapNode
import ru.boringowl.parapp.presentation.repository.RoadmapsRepository
import java.text.SimpleDateFormat
import java.util.*

class RoadmapsMockRepository : RoadmapsRepository {
    var data: MutableLiveData<List<Roadmap>>
    var list: List<Roadmap>
    var listMock: List<Roadmap>

    init {
        val roadmaps = listOf (
            Roadmap(
                null,
                "Дорожка 3",
                null,
                SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.ENGLISH).format(Date()),
                listOf("Категория 11","Категория 12","Категория 13"),
                "Описание поста. Описание поста. Описание поста. Описание поста Наверное",
                1,
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
        list = listOf()
        listMock = roadmaps
        data = MutableLiveData(list)
    }

    override fun <T : Roadmap> getAllRoadmaps(): LiveData<List<T>> {
        return data as LiveData<List<T>>
    }

    override fun <T : Roadmap> getAllRoadmaps(topicId: Int): LiveData<List<T>> {
        return data as LiveData<List<T>>
    }
    override suspend fun <T : Roadmap> addRoadmap(roadmap: T) {
        roadmap.id = list.size
        list = list + roadmap
        data.value = list
    }

    override fun <T : Roadmap> getRoadmap(roadmapId: Int): T {
        list.forEach {
            if (it.id == roadmapId)
                return it as T
        }
        return Roadmap(0, "", null, "", listOf(""), "", 1, RoadmapNode("", "", listOf())) as T
    }

    override suspend fun <T : Roadmap> deleteRoadmap(roadmap: T) {
        list = list - roadmap
        data.value = list
    }
}