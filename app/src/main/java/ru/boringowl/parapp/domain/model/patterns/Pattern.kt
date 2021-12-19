package ru.boringowl.parapp.domain.model.patterns

import java.util.*

open class Pattern(
    open var patternId: UUID?,
    open val title: String,
    open val description: String,
    open val problem: String,
    open val solution: String,
    open val solutionInCode: String, //псевдокод решения
    open val useCase: String,
    open val features: List<PatternFeature>,
    open val type: PatternType,
    open val difficulty: Int,
    open val localId: Int? = null,
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