package ru.boringowl.parapp.presentation.repository.mock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.domain.model.posts.roadmaps.Roadmap
import ru.boringowl.parapp.domain.model.posts.roadmaps.RoadmapNode
import ru.boringowl.parapp.domain.model.user.User
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
                SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.ENGLISH).format(Date()),
                listOf("Категория 11","Категория 12","Категория 13"),
                "Описание поста. Описание поста. Описание поста. Описание поста Наверное",
                Topic(null, "", User(), arrayListOf()),
                null,
                false,
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
                            "Накрытьcя одеялом",
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

    override fun <T : Roadmap> getAllRoadmaps(topicId: UUID): LiveData<List<T>> {
        return data as LiveData<List<T>>
    }
    override suspend fun <T : Roadmap> addRoadmap(roadmap: T) {
        roadmap.postId = UUID.randomUUID()
        list = list + roadmap
        data.value = list
    }

    override fun <T : Roadmap> getRoadmap(roadmapId: UUID): T {
        list.forEach {
            if (it.postId == roadmapId)
                return it as T
        }
        return Roadmap(UUID.randomUUID(), "", null, "", listOf(""), "",
            Topic(null, "", User(), arrayListOf()), null, false, RoadmapNode("", "", listOf())) as T
    }

    override suspend fun <T : Roadmap> deleteRoadmap(roadmap: T) {
        list = list - roadmap
        data.value = list
    }
}