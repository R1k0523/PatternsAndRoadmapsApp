package ru.boringowl.parapp.presentation.repository.model.patterns

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.boringowl.parapp.domain.model.patterns.PatternFeature
import ru.boringowl.parapp.domain.model.patterns.PatternInfo


@Entity(tableName = "patterns")
data class PatternInfoDTO(
    @ColumnInfo(name = "title")
    override val title: String,
    @ColumnInfo(name = "description")
    override val description: String,
    @ColumnInfo(name = "problem")
    override val problem: String,
    @ColumnInfo(name = "solution")
    override val solution: String,
    @ColumnInfo(name = "solution_in_code")
    override val solutionInCode: String, //псевдокод решения
    @ColumnInfo(name = "use_case")
    override val useCase: String,
    @ColumnInfo(name = "feature")
    override val feature: List<PatternFeature>,
    @ColumnInfo(name = "type")
    override val type: PatternType,
    @ColumnInfo(name = "difficulty")
    override val difficulty: Int,
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,

    ) : PatternInfo(
    id,
    title,
    description,
    problem,
    solution,
    solutionInCode,
    useCase,
    feature,
    type,
    difficulty,
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

}