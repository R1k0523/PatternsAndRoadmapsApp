package ru.boringowl.parapp.domain.model.patterns

data class PatternInfo(
    val title: String,
    val description: String,
    val problem: String,
    val solution: String,
    val solutionInCode: String, //псевдокод решения
    val useCase: String,
    val feature: PatternFeature,
    val type: PatternType
) {
    enum class PatternType {
        CREATIONAL, STRUCTURAL, BEHAVIORAL
    }
}