package edu.miu.cs473de.cv_builder.common

import android.content.SharedPreferences
import androidx.room.Database
import edu.miu.cs473de.cv_builder.database.AppDatabase
import edu.miu.cs473de.cv_builder.database.repositories.UserRepository

object CommonConstrains {

    var DB:AppDatabase?=null
    var MSharedPreference:SharedPreferences?=null
    var USER_REPO:UserRepository?=null
}