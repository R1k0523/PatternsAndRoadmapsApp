package ru.boringowl.parapp.presentation.repository.model.patterns

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.boringowl.parapp.domain.model.patterns.PatternFeature
import ru.boringowl.parapp.domain.model.patterns.Pattern
import java.util.*


@Entity(tableName = "patterns")
data class PatternDTO(
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
    override val features: List<PatternFeature>,
    @ColumnInfo(name = "type")
    override val type: PatternType,
    @ColumnInfo(name = "difficulty")
    override val difficulty: Int,
    @ColumnInfo(name = "pattern_id")
    override var patternId: UUID? = null,
    @PrimaryKey(autoGenerate = true)
    override var localId: Int? = null
    ) : Pattern(
    patternId,
    title,
    description,
    problem,
    solution,
    solutionInCode,
    useCase,
    features,
    type,
    difficulty,
) {

    constructor(pattern: Pattern) : this(
        pattern.title,
        pattern.description,
        pattern.problem,
        pattern.solution,
        pattern.solutionInCode,
        pattern.useCase,
        pattern.features,
        pattern.type,
        pattern.difficulty,
        pattern.patternId,
        pattern.localId
    )

}