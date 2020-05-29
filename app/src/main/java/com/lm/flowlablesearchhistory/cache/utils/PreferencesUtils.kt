package com.lm.flowlablesearchhistory.cache.utils

import android.content.Context
import android.content.SharedPreferences

object PreferencesUtils {
    fun putString(sp: SharedPreferences, key: String, value: String) {
        val editor = sp.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getString(sp: SharedPreferences, key: String): String {
        return sp.getString(key, "") ?: ""
    }

    fun putArrays(sp: SharedPreferences, values: Map<String, String>) {
        val editor = sp.edit()
        for ((key, value) in values) {
            editor.putString(key, value)
        }
        editor.commit()
    }

    fun putInt(sp: SharedPreferences, key: String, value: Int) {
        val editor = sp.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun getInt(sp: SharedPreferences, key: String, defaultvalue: Int): Int {
        return sp.getInt(key, defaultvalue)
    }

    fun putLong(sp: SharedPreferences, key: String, value: Long) {
        val editor = sp.edit()
        editor.putLong(key, value)
        editor.commit()
    }

    fun getLong(sp: SharedPreferences, key: String, defaultvalue: Long): Long {
        return sp.getLong(key, defaultvalue)
    }

    fun putBoolean(sp: SharedPreferences, key: String, value: Boolean) {
        val editor = sp.edit()
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun getBoolean(sp: SharedPreferences, key: String, defaultvalue: Boolean): Boolean {
        return sp.getBoolean(key, defaultvalue)
    }

    fun deletePreferrencesForSetting(context: Context, xmlName: String, albumId: Int) {
        val settings = context.getSharedPreferences(xmlName, 0)
        val setEditor = settings.edit()
        setEditor.remove(albumId.toString() + "")
        setEditor.commit()
    }

    fun clearPreferrencesForSetting(context: Context, xmlName: String) {
        val settings = context.getSharedPreferences(xmlName, 0)
        settings.edit().clear().commit()
    }
}
