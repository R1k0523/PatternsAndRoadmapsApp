package ru.boringowl.parapp.domain.model.patterns

open class Pattern(
    open var id: Int?,
    open val title: String,
    open val description: String,
    open val problem: String,
    open val solution: String,
    open val solutionInCode: String, //псевдокод решения
    open val useCase: String,
    open val feature: List<PatternFeature>,
    open val type: PatternType,
    open val difficulty: Int,

    ) {
    enum class PatternType(val title: String) {
        CREATIONAL("Порождающий"), STRUCTURAL("Структурный"), BEHAVIORAL("Поведенческий"), UNKNOWN("Неизвестный")

    }

    companion object {
        fun getType(title: String): PatternType {
            return when (title) {
                PatternType.STRUCTURAL.title -> PatternType.STRUCTURAL
                PatternType.CREATIONAL.title -> PatternType.CREATIONAL
                PatternType.BEHAVIORAL.title -> PatternType.BEHAVIORAL
                else -> PatternType.UNKNOWN
            }
        }
    }
}