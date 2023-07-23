package com.example.loginmvvm.common.data.local.cache

import android.content.Context
import com.example.loginmvvm.BuildConfig.APPLICATION_ID

class CacheImpl(context: Context) : Cache {

    private val preferencesFilename = APPLICATION_ID
    private val sharedPreferences = context.getSharedPreferences(preferencesFilename, 0)

    override suspend fun insert(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override suspend fun update(key: String, value: String) {
        insert(key, value)
    }

    override suspend fun delete(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    override suspend fun get(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }

    companion object{
        const val USER_KEEP_LOGGED = "KEEP_LOGGED"
        const val USER_ID = "USER_ID"
        const val USER_DATA = "DATA_USER"
    }
}

interface Cache {
    suspend fun insert(key: String, value: String)
    suspend fun update(key: String, value: String)
    suspend fun delete(key: String)
    suspend fun get(key: String): String
}