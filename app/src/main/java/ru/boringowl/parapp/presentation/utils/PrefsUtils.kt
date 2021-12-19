package ru.boringowl.parapp.presentation.utils

import android.content.Context
import android.util.Log

class PrefsUtils(context: Context) {
    val prefs = context.getSharedPreferences("default", Context.MODE_PRIVATE)

    fun getToken() : String? = prefs.getString("user_token", null)
    fun getRefreshToken() : String? = prefs.getString("refresh_token", null)
    fun isTokenStored() : Boolean = prefs.getString("user_token", null) != null
    fun isRefreshTokenStored() : Boolean = prefs.getString("refresh_token", null) != null

    fun provideTokensInfo(token: String, refreshToken: String) {
        val editor = prefs.edit()
        editor.putString("user_token", token)
        editor.putString("refresh_token", refreshToken)
        editor.apply()
    }
    fun deleteTokens() {
        val editor = prefs.edit()
        editor.remove("user_token")
        editor.remove("refresh_token")
        editor.apply()
    }
}
