package ru.boringowl.parapp.presentation.repository.model.notes.noteroadmap

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roadmap_nodes")
class RoadmapNodeDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val childrenNodes: List<RoadmapNodeDTO>,
) {}