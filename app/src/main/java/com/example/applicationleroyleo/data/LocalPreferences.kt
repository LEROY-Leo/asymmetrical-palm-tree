package com.example.applicationleroyleo.data

import android.content.Context
import android.content.SharedPreferences


class LocalPreferences private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)

    fun saveStringValue(yourValue: String?) {
        println("Where does it bug???")
        sharedPreferences.edit().putString("saveStringValue", yourValue).apply()
    }

    fun getSaveStringValue(): String? {
        return sharedPreferences.getString("saveStringValue", null)
    }

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

    fun addToHistory(newEntry: String){
        val history = this.getHistory()
        if (history != null) {
            history.add(newEntry)
        }
        sharedPreferences.edit().putStringSet("histories", history).apply()
    }

    fun getHistory(): MutableSet<String>? {
        return sharedPreferences.getStringSet("histories", mutableSetOf())
    }


    fun clearHistory() {

        val history = this.getHistory()
        if (history != null) {
            history.clear()
        }
        sharedPreferences.edit().putStringSet("histories", history).apply()
    }


}
