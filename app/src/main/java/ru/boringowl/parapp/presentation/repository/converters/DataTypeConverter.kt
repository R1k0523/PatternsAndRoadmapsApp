package ru.boringowl.parapp.presentation.repository.converters

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken

import com.google.gson.Gson
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.domain.model.posts.notes.NoteSection
import ru.boringowl.parapp.domain.model.posts.roadmaps.RoadmapNode
import ru.boringowl.parapp.domain.model.patterns.PatternFeature
import ru.boringowl.parapp.domain.model.patterns.Pattern
import ru.boringowl.parapp.domain.model.posts.Topic
import ru.boringowl.parapp.domain.model.user.User
import java.lang.reflect.Type
import java.util.*


object DataTypeConverter {
    private val gson by inject(Gson::class.java)

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
    @TypeConverter
    fun stringToUser(user: String): User {
        return gson.fromJson(user, User::class.java)
    }

    @TypeConverter
    fun userToString(user: User): String {
        return gson.toJson(user)
    }
    @TypeConverter
    fun stringToTopic(topic: String): Topic {
        return gson.fromJson(topic, Topic::class.java)
    }

    @TypeConverter
    fun topicToString(topic: Topic): String {
        return gson.toJson(topic)
    }

    @TypeConverter
    fun stringToUserList(data: String?): List<User> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<User?>?>() {}.type
        return gson.fromJson(data, listType)
    }
    @TypeConverter
    fun userListToString(someObjects: List<User?>?): String {
        return gson.toJson(someObjects)
    }
    @TypeConverter
    fun stringToRole(title: String): User.Roles {
        return User.Roles.USER.getRole(title)
    }
    @TypeConverter
    fun roleToString(role: User.Roles): String {
        return role.title
    }
    @TypeConverter
    fun stringToList(listOfStrings: String): List<String> {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson(listOfStrings, listType)
    }
    @TypeConverter
    fun listToString(listOfStrings: List<String>): String {
        return gson.toJson(listOfStrings)
    }
    @TypeConverter
    fun stringToUuid(id: String):UUID {
        return UUID.fromString(id)
    }
    @TypeConverter
    fun uuidToString(id: UUID): String {
        return id.toString()
    }
}