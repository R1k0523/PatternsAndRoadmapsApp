package ru.boringowl.parapp.domain.model.patterns

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "patterns")
data class PatternInfoDTO(
    val title: String,
    val description: String,
    val problem: String,
    val solution: String,
    val solutionInCode: String, //псевдокод решения
    val useCase: String,
    val feature: List<PatternFeature>, // TODO serialize pattern features
    val type: PatternInfo.PatternType,
    val difficulty: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    ) {

    constructor(patternInfo: PatternInfo) : this(
        patternInfo.title,
        patternInfo.description,
        patternInfo.problem,
        patternInfo.solution,
        patternInfo.solutionInCode,
        patternInfo.useCase,
        patternInfo.feature,
        patternInfo.type,
        patternInfo.difficulty,
    )


    fun toPatternInfo() = PatternInfo(
        title,
        description,
        problem,
        solution,
        solutionInCode,
        useCase,
        feature,
        type,
        difficulty,

    )

}