package ru.boringowl.parapp.domain.model.patterns

data class PatternInfo(
    val id: Int,
    val title: String,
    val description: String,
    val problem: String,
    val solution: String,
    val solutionInCode: String, //псевдокод решения
    val useCase: String,
    val feature: List<PatternFeature>,
    val type: PatternType,
    val difficulty: Int,

    ) {
    enum class PatternType(val title: String) {
        CREATIONAL("Порождающий"), STRUCTURAL("Структурный"), BEHAVIORAL("Поведенческий")
    }
}