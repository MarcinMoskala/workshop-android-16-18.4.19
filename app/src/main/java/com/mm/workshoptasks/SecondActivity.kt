package com.mm.workshoptasks

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private val text by argString(TEXT_ARG)
    private val user by arg<User>(USER_ARG)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        textView.text = getString(R.string.argumets_display, text, user.fullName)
        takePhotoButton.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when {
            requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK -> {
                val imageBitmap = data?.extras?.get("data") as Bitmap
                imageView.setImageBitmap(imageBitmap)
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        private const val TEXT_ARG = "TEXT_ARG"
        private const val USER_ARG = "USER_ARG"

        private const val REQUEST_IMAGE_CAPTURE = 1

        fun start(activity: Activity, message: String, user: User) {
            val intent = Intent(activity, SecondActivity::class.java).apply {
                putExtra(TEXT_ARG, message)
                putExtra(USER_ARG, user)
            }
            activity.startActivity(intent)
        }
    }
}

fun Activity.argString(key: String) = lazy { intent.getStringExtra(key) }
fun <T: Parcelable> Activity.arg(key: String) = lazy { intent.getParcelableExtra<T>(key) }
