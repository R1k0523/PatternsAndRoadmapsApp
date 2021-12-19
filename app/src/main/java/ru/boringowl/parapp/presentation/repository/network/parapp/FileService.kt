package ru.boringowl.parapp.presentation.repository.network.parapp

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import org.koin.java.KoinJavaComponent
import ru.boringowl.parapp.presentation.repository.network.BaseService
import ru.boringowl.parapp.presentation.repository.network.MyResult
import ru.boringowl.parapp.presentation.repository.network.parapp.response.UploadFileResponse
import ru.boringowl.parapp.presentation.utils.PrefsUtils

class FileService(private val api: ParappDataAPI) : BaseService() {
    private val prefs by KoinJavaComponent.inject(PrefsUtils::class.java)
    suspend fun uploadFiles(token: String, files: Array<MultipartBody.Part>) : MyResult<List<UploadFileResponse>> =
        createCall { api.uploadFiles(token, files)}
    suspend fun uploadFile(token: String,  file: MultipartBody.Part) : MyResult<UploadFileResponse> =
        createCall { api.uploadFile(token, file)}
    suspend fun uploadFiles(fileName: String) : MyResult<ResponseBody> =
        createCall { api.uploadFiles(fileName)}
}