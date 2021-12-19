package ru.boringowl.parapp.domain.model.patterns

import java.util.*

data class PatternFeature(
    val title: String,
    val isAdvantage: Boolean,
    val featureId: UUID? = null,
)
