package dev.mysticmaster.tamngon247.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream

fun bitmapToFile(bitmap: Bitmap, context: Context): File {
    val outputDir = context.cacheDir
    val outputFile = File(outputDir, generateRandomString()+".jpeg")

    val fos = FileOutputStream(outputFile)
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
    fos.close()

    return outputFile
}

fun uriToBitmap(context: Context, selectedPhoto: Uri): Bitmap {
    return MediaStore.Images.Media.getBitmap(context.contentResolver, selectedPhoto)
}