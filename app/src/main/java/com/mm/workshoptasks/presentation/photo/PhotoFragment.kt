package com.mm.workshoptasks.presentation.photo

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mm.workshoptasks.R
import kotlinx.android.synthetic.main.fragment_photo.imageView
import kotlinx.android.synthetic.main.fragment_photo.takePhotoButton

class PhotoFragment : Fragment(), PhotoView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_photo, container, false)

    private val presenter by lazy { PhotoPresenter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        takePhotoButton.setOnClickListener {
            presenter.onTakePhoto()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when {
            requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK -> {
                val imageBitmap = data?.extras?.get("data") as Bitmap
                presenter.onPhotoReturned(imageBitmap)
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun startCameraForPhoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(activity!!.packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun displayImage(imageBitmap: Bitmap) {
        imageView.setImageBitmap(imageBitmap)
    }

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 112
    }
}
