package ru.boringowl.parapp.presentation.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity


class FileUtils {
    companion object {
        fun getFileName(context: Context, uri: Uri): String? {
            return try {
                val cursor = context.contentResolver.query(uri, null, null, null, null)
                cursor?.moveToFirst()
                val fileName = cursor?.getString(cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME))
                cursor?.close()
                fileName
            } catch (e: Exception) {
                null
            }
        }
        fun stringsToUriList(stringUris: List<String>?, context: Context) : ArrayList<Uri> {
            val uris = arrayListOf<Uri>()
            stringUris?.forEach {
                val uri = Uri.parse(it)
                if (getFileName(context, uri) != null)
                    uris.add(uri)
            }
            return uris
        }
        fun getFile(activity: FragmentActivity, fileTypes: Array<String>, toDo: (result: Uri) -> Unit) {
            activity.activityResultRegistry.register(
                "key", ActivityResultContracts.OpenDocument()
            ) { result ->
                if (result != null) {
                    activity.applicationContext.contentResolver
                        .takePersistableUriPermission(result, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    toDo(result)
                }
            }.launch(fileTypes)
        }
    }
}

