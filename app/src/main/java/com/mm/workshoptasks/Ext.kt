package com.mm.workshoptasks

import android.app.Activity
import android.content.Context
import android.os.Parcelable
import android.widget.Toast

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.argString(key: String) = lazy { intent.getStringExtra(key) }
fun <T: Parcelable> Activity.arg(key: String) = lazy { intent.getParcelableExtra<T>(key) }