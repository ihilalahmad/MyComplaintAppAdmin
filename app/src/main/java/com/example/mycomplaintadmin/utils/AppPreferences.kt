package com.example.mycomplaintadmin.utils

import android.content.Context
import com.example.mycomplaintadmin.utils.PreferenceKeys.Companion.DEPT_NAME
import com.example.mycomplaintadmin.utils.PreferenceKeys.Companion.DEPT_NAME_PREFS
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

        fun setDeptName(context: Context, deptName: String) {
            val editor = context
                .getSharedPreferences(DEPT_NAME_PREFS, Context.MODE_PRIVATE)
                .edit()
            editor.putString(DEPT_NAME, deptName)
            editor.apply()
        }

        fun getDeptName(context: Context) : String {
            val prefs =
                context.getSharedPreferences(DEPT_NAME_PREFS, Context.MODE_PRIVATE)
            return prefs.getString(DEPT_NAME, "")!!
        }

    }
}