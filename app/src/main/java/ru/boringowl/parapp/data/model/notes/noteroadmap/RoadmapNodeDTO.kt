package ru.boringowl.parapp.data.model.notes.noteroadmap

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roadmap_nodes")
class RoadmapNodeDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val description: String,
    @ColumnInfo
    val childrenNodes: List<RoadmapNodeDTO>,
) {}