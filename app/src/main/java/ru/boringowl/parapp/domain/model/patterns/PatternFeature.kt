package ru.boringowl.parapp.domain.model.patterns

data class PatternFeature(
    val title: String,
    val featureType: FeatureType,
) {
    enum class FeatureType {
        GOOD, BAD, NEUTRAL
    }
}
