package ru.boringowl.parapp.domain.model.posts.notes

data class NoteSection(
    var description: String,
    var docs: List<String>,
    var image: List<String>,
    var linkItems: List<String>,
)