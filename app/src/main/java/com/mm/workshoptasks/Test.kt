package com.mm.workshoptasks

import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.InputStream
import java.io.ObjectInputStream
import java.util.zip.ZipInputStream

var user: User? = null

fun main() {
    // Instead of
//    val user = user
//    if(user != null) {
//        print(user.name)
//    }

    // We can
    user?.let {
        print(it.name)
    }

//    var fis: InputStream = FileInputStream("/someFile.gz")
//    var bis: InputStream = BufferedInputStream(fis)
//    var gis: InputStream = ZipInputStream(bis)
//    var ois = ObjectInputStream(gis)
//    var someObject = ois.readObject()

    var obj = FileInputStream("/someFile.gz")
        .let(::BufferedInputStream)
        .let(::ZipInputStream)
        .let(::ObjectInputStream)
        .readObject()

    (1..100)
        .map { it * 2 }
        .filter { it % 3 == 0 }
        .flatMap { listOf(it, it + 100) }
        .joinToString()
        .let(::print)
}