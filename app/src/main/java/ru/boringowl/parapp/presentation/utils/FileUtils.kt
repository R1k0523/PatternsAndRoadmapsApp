package ru.boringowl.parapp.presentation.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns


class FileUtils {
    companion object {
        fun getFileName(context: Context, uri: Uri): String? {
            try {
                val cursor = context.contentResolver.query(uri, null, null, null, null)
                cursor?.moveToFirst()
                val fileName = cursor?.getString(cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME))
                cursor?.close()
                return fileName
            } catch (e: Exception) {
                throw e
            }
        }
    }
}