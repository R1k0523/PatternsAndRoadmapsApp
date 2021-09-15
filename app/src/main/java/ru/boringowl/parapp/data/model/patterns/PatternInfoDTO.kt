package ru.boringowl.parapp.domain.model.patterns

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "patterns")
data class PatternInfoDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val description: String,
    @ColumnInfo
    val problem: String,
    @ColumnInfo
    val solution: String,
    @ColumnInfo
    val solutionInCode: String, //псевдокод решения
    @ColumnInfo
    val useCase: String,
    @ColumnInfo
    val feature: String, // TODO serialize pattern features
    val type: PatternType,
    val difficulty: Int,

    ) {
    enum class PatternType {
        CREATIONAL, STRUCTURAL, BEHAVIORAL
    }
}