package com.example.booking

import android.content.Context
import android.content.SharedPreferences

class SessionManager : SharedPreferences {
    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    var context: Context
    var PRIVATE_MODE: Int = 0

    constructor(context: Context) {

        this.context = context
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    companion object {
        const val PREF_NAME: String = "token"
        const val IS_LOGIN: String = "isLogin"
        const val KEY_EMAIL: String = "email"
        const val KEY_SINCE: String = "since"
    }
    fun createLoginSession(token: String, email: String, sinceTime: Long) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(PREF_NAME, token)
        editor.putString(KEY_EMAIL, email)
        editor.putLong(KEY_SINCE, sinceTime)

        editor.apply()
    }

    fun checkLogin(): Boolean {

        return !this.isLoggedIn()
    }

    fun logoutUser() {
        editor.clear()
        editor.apply()
    }

    fun editEmail(email: String) {
        editor.putString(KEY_EMAIL , email)
        editor.apply()
    }

    fun getEmail(): String {
        return pref.getString(KEY_EMAIL, "").toString()
    }

    fun getTime(): String {
        return pref.getLong(KEY_SINCE, 0).toString()
    }

    override fun edit(): SharedPreferences.Editor {
        TODO("Not yet implemented")
    }

    fun isLoggedIn(): Boolean {

        return pref.getBoolean(IS_LOGIN, false)
    }

    fun getToken(): String {

        return pref.getString(PREF_NAME, "").toString()
    }

    override fun getAll(): MutableMap<String, *> {
        TODO("Not yet implemented")
    }

    override fun getString(key: String?, defValue: String?): String? {
        TODO("Not yet implemented")
    }

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String> {
        TODO("Not yet implemented")
    }

    override fun getInt(key: String?, defValue: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getLong(key: String?, defValue: Long): Long {
        TODO("Not yet implemented")
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        TODO("Not yet implemented")
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(key: String?): Boolean {
        TODO("Not yet implemented")
    }



    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        TODO("Not yet implemented")
    }

    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        TODO("Not yet implemented")
    }
}