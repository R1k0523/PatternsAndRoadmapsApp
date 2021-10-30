package ru.boringowl.parapp.presentation.utils

import android.content.Context
import android.util.Log

class PrefsUtils(context: Context) {
    val prefs = context.getSharedPreferences("default", Context.MODE_PRIVATE)

    fun getToken() : String? {
        Log.d("kekes1", "token="+prefs.getString("user_token", null).toString())
        return prefs.getString("user_token", null)
    }

    fun provideToken(token: String) {
        prefs.edit().putString("user_token", token).apply()
        Log.d("kekes2", "token="+prefs.getString("user_token", null).toString())
    }
    fun isTokenStored() : Boolean = prefs.getString("user_token", null) != null
    fun deleteToken() {
        prefs.edit().remove("user_token").apply()
    }
}