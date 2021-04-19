package com.example.applicationleroyleo.data

import android.content.Context
import android.content.SharedPreferences

// This class handles the saving of position data
class LocalPreferences private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)

    companion object {
        private var INSTANCE: LocalPreferences? = null

        fun getInstance(context: Context): LocalPreferences {
            return INSTANCE?.let {
                INSTANCE
            } ?: run {
                INSTANCE = LocalPreferences(context)
                return INSTANCE!!
            }
        }
    }

    // save a position in a position history
    fun addToHistory(newEntry: String){
        val history = this.getHistory()
        history?.add(newEntry)
        sharedPreferences.edit().putStringSet("histories", history).apply()
    }

    // return a position history
    fun getHistory(): MutableSet<String>? {
        return sharedPreferences.getStringSet("histories", mutableSetOf())
    }

    // clear the position history
    fun clearHistory() {

        val history = this.getHistory()
        history?.clear()
        sharedPreferences.edit().putStringSet("histories", history).apply()
    }
}
