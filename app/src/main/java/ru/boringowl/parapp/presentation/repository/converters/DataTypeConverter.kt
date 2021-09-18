package ru.boringowl.parapp.presentation.repository.converters

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken

import com.google.gson.Gson
import ru.boringowl.parapp.domain.model.notes.notelinks.NoteSection
import ru.boringowl.parapp.domain.model.notes.noteroadmap.RoadmapNode
import ru.boringowl.parapp.domain.model.patterns.PatternFeature
import ru.boringowl.parapp.domain.model.patterns.Pattern
import java.lang.reflect.Type
import java.util.*


object DataTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToNoteLinksSectionDTO(data: String?): List<NoteSection> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<NoteSection?>?>() {}.type
        return gson.fromJson(data, listType)
    }
    @TypeConverter
    fun noteLinksSectionDTOToString(someObjects: List<NoteSection?>?): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToPatternFeature(data: String): List<PatternFeature> {
        val listType: Type = object : TypeToken<List<PatternFeature?>?>() {}.type
        return gson.fromJson(data, listType)
    }
    @TypeConverter
    fun patternFeatureToString(someObjects: List<PatternFeature>): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun patternTypeToString(patternType: Pattern.PatternType): String {
        return patternType.title
    }
    @TypeConverter
    fun stringToPatternType(patternType: String): Pattern.PatternType {
        return Pattern.getType(patternType)
    }

    @TypeConverter
    fun stringToNodeTree(node: String): RoadmapNode {
        return gson.fromJson(node, RoadmapNode::class.java)
    }
    @TypeConverter
    fun nodeTreeToString(node: RoadmapNode): String {
        return gson.toJson(node)
    }
}