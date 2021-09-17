package ru.boringowl.parapp.presentation.repository.converters

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken

import com.google.gson.Gson
import ru.boringowl.parapp.presentation.repository.model.notes.notelinks.NoteLinksSectionDTO
import ru.boringowl.parapp.domain.model.patterns.PatternFeature
import java.lang.reflect.Type
import java.util.*


object DataTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToNoteLinksSectionDTO(data: String?): List<NoteLinksSectionDTO> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<NoteLinksSectionDTO?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun noteLinksSectionDTOToString(someObjects: List<NoteLinksSectionDTO?>?): String {
        return gson.toJson(someObjects)
    }
    @TypeConverter
    fun stringToPatternFeature(data: String): List<PatternFeature> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<PatternFeature?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun patternFeatureToString(someObjects: List<PatternFeature>): String {
        return gson.toJson(someObjects)
    }
}