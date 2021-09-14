package ru.boringowl.parapp.domain.model.patterns

data class PatternInfo(
    val title: String,
    val description: String,
    val problem: String,
    val solution: String,
    val solutionInCode: String, //псевдокод решения
    val useCase: String,
    val feature: PatternFeature,
    val type: PatternType,
    val difficulty: Int,
) {
    enum class PatternType {
        CREATIONAL, STRUCTURAL, BEHAVIORAL
    }
}