package ru.boringowl.parapp.domain.model.patterns

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "patterns")
data class PatternInfoDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val problem: String,
    val solution: String,
    val solutionInCode: String, //псевдокод решения
    val useCase: String,
    val feature: List<PatternFeature>, // TODO serialize pattern features
    val type: PatternInfo.PatternType,
    val difficulty: Int,

    ) {

    constructor(patternInfo: PatternInfo) : this(
        0,
        patternInfo.title,
        patternInfo.description,
        patternInfo.problem,
        patternInfo.solution,
        patternInfo.solutionInCode,
        patternInfo.useCase,
        patternInfo.feature,
        patternInfo.type,
        patternInfo.difficulty,
    ) {}


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