package ru.boringowl.parapp.domain.model.notes.noteroadmap

import ru.boringowl.parapp.data.model.notes.noteroadmap.RoadmapNodeDTO
import ru.boringowl.parapp.domain.model.notes.Note
import ru.boringowl.parapp.domain.model.notes.NoteCategory
import java.time.LocalDateTime

data class NoteRoadmap(
    override val title: String,
    override val publicationDateTime: LocalDateTime,
    override val noteCategories: List<NoteCategory>,
    override val noteDescription: String,
    val root: RoadmapNodeDTO,
) : Note(
    title,
    publicationDateTime,
    noteCategories,
    noteDescription,
) {}