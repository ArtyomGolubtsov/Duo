package com.example.duo.data

import android.content.Context
import androidx.core.content.edit
import com.example.duo.presentation.Qwestion.Users


private const val PREFS_NAME = "app_prefs"
private const val KEY_FIRST_LAUNCH = "first_launch_key"

private const val DATE_START_VMESTE = "date_start"

//Константы для мужчины
private const val NAME_MAN = "name_man"
private const val DOB_MAN = "dob_man"
private const val AVA_MAN = "ava_man"

//Константы для женщины
private const val NAME_WOMAN = "name_woman"
private const val DOB_WOMAN = "dob_woman"
private const val AVA_WOMAN = "ava_woman"

object PrefSetting {

    fun getFirstLaunchApp(context: Context): Boolean{
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_FIRST_LAUNCH, true)
    }

    fun setFirstLaunchApp(context: Context, state: Boolean){
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit() { putBoolean(KEY_FIRST_LAUNCH, state)}
    }

    fun getDateOfRelationship(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(DATE_START_VMESTE, "")
    }

    fun setDateOfRelationship(context: Context, date: String){
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit() { putString(DATE_START_VMESTE, date)}
    }

    fun getUserInfo(context: Context, sex: Boolean): Users {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        if(sex) {
             val name =  prefs.getString(NAME_MAN,"")
             val dob =  prefs.getString(DOB_MAN,"")
            val ava = prefs.getString(AVA_MAN,"")
            return Users(
                name = name?:"",
                sex = sex,
                dateOfBitrh = dob?:"",
                photoPath = ava?:""
            )
        }
        else if(!sex){
            val name =  prefs.getString(NAME_WOMAN,"")
            val dob =  prefs.getString(DOB_WOMAN,"")
            val ava = prefs.getString(AVA_WOMAN,"")
            return Users(name = name?:"", sex = false, dateOfBitrh = dob?:"",
                photoPath = ava?:"")
        }else{
            return  Users(name ="", sex = false, dateOfBitrh = "",
                photoPath = "")
        }
    }

    fun setUserInfo(context: Context, user: Users){
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        if(user.sex){
            prefs.edit() { putString(NAME_MAN, user.name)}
            prefs.edit() { putString(DOB_MAN, user.dateOfBitrh)}
            prefs.edit(){putString(AVA_MAN, user.photoPath)}
        }else
        {
            prefs.edit() { putString(NAME_WOMAN, user.name)}
            prefs.edit() { putString(DOB_WOMAN, user.dateOfBitrh)}
            prefs.edit(){putString(AVA_WOMAN, user.photoPath)}
        }

    }

}