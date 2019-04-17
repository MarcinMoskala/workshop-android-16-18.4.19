package com.mm.workshoptasks

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech

class AndroidSpeaker: Speaker {

    lateinit var tts: TextToSpeech

    fun init(context: Context) {
        tts = TextToSpeech(context, null)
    }

    override fun speak(text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    fun onDestroy() {
        tts.shutdown()
    }
}

interface Speaker {
    fun speak(text: String)
}