package com.mm.workshoptasks.presentation.photo

import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore

class PhotoPresenter(
    private val view: PhotoView
) {
    fun onTakePhoto() {
        view.startCameraForPhoto()
    }

    fun onPhotoReturned(imageBitmap: Bitmap) {
        view.displayImage(imageBitmap)
    }
}