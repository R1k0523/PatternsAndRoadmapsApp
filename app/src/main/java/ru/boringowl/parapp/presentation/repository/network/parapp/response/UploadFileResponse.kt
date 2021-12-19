package ru.boringowl.parapp.presentation.repository.network.parapp.response

class UploadFileResponse(
    var fileName: String,
    var fileDownloadUri: String,
    var fileType: String,
    var size: Long
)