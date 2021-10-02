package ru.boringowl.parapp.presentation.utils

import android.R.attr
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.R.attr.data
import android.provider.MediaStore
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


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