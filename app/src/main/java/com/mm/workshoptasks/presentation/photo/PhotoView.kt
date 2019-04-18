package com.mm.workshoptasks.presentation.photo

import android.graphics.Bitmap

interface PhotoView {
    fun startCameraForPhoto()
    fun displayImage(imageBitmap: Bitmap)
}
