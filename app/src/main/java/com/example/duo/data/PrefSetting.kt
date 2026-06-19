package com.example.duo.data

import android.content.Context
import androidx.core.content.edit

private const val PREFS_NAME = "app_prefs"
private const val KEY_FIRST_LAUNCH = "first_launch_key"

object PrefSetting {

    fun getFirstLaunchApp(context: Context): Boolean{
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_FIRST_LAUNCH, true)
    }

    fun setFirstLaunchApp(context: Context){
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit() { putBoolean(KEY_FIRST_LAUNCH, true)}
    }

}