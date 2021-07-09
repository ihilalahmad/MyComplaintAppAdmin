package com.example.mycomplaintadmin.utils

import android.content.Context
import com.example.mycomplaintadmin.utils.PreferenceKeys.Companion.LOGIN_PREFS
import com.example.mycomplaintadmin.utils.PreferenceKeys.Companion.LOGIN_STATUS

class AppPreferences {

    companion object {

        fun setLoginState(context: Context, isLoggedIn: Boolean) {
            val editor = context
                .getSharedPreferences(LOGIN_PREFS, Context.MODE_PRIVATE)
                .edit()
            editor.putBoolean(LOGIN_STATUS, isLoggedIn)
            editor.apply()
        }

        fun getLoginStatus(context: Context): Boolean {
            val prefs =
                context.getSharedPreferences(LOGIN_PREFS, Context.MODE_PRIVATE)
            return prefs.getBoolean(LOGIN_STATUS, false)
        }

    }
}