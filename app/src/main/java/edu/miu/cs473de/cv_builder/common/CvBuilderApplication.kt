package edu.miu.cs473de.cv_builder.common

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import edu.miu.cs473de.cv_builder.common.CommonConstrains.DB
import edu.miu.cs473de.cv_builder.common.CommonConstrains.MSharedPreference
import edu.miu.cs473de.cv_builder.common.CommonConstrains.USER_REPO
import edu.miu.cs473de.cv_builder.database.AppDatabase
import edu.miu.cs473de.cv_builder.database.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CvBuilderApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val applicationScope= CoroutineScope(SupervisorJob())

        if (applicationScope!=null) {
            DB = AppDatabase.getDatabase(applicationContext, applicationScope)
            if (DB!=null)
                USER_REPO= UserRepository(DB?.userDao()!!)
        }
        MSharedPreference=getSharedPreferences("cv_builder",MODE_PRIVATE)
    }
}