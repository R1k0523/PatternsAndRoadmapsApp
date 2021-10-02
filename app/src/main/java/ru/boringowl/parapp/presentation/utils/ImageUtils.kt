package ru.boringowl.parapp.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri

class ImageUtils {
    companion object {
        fun uriToBitmap(context: Context, uri: Uri): Bitmap? {
            return BitmapFactory.decodeFileDescriptor(
                context.contentResolver.openFileDescriptor(
                    uri, "r"
                )?.fileDescriptor
            )
        }
    }
}

