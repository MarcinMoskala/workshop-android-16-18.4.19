package com.mm.workshoptasks

import android.content.Context
import android.preference.PreferenceManager

class PrefRepo(context: Context) {

    private val pref by lazy { PreferenceManager.getDefaultSharedPreferences(context) }

    fun getEntryCount(): Int = pref.getInt(ENTRY_COUNT_KEY, 0)

    fun setEntryCount(num: Int) {
        pref.edit().putInt(ENTRY_COUNT_KEY, num).apply()
    }

    fun getEmail(): String? = pref.getString(EMAIL_KEY, null)

    fun setEmail(email: String) {
        pref.edit().putString(EMAIL_KEY, email).apply()
    }

    fun removeEmail() {
        pref.edit().remove(EMAIL_KEY).apply()
    }

    companion object {
        private const val ENTRY_COUNT_KEY = "ENTRY_COUNT_KEY"
        private const val EMAIL_KEY = "EMAIL_KEY"
    }
}