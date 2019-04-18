package com.mm.workshoptasks.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
class User(
    val name: String,
    val surname: String
): Parcelable {
    val fullName: String
        get() = "$name $surname"
}