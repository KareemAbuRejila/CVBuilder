package edu.miu.cs473de.cv_builder.common

import android.app.Application
import androidx.room.Room
import edu.miu.cs473de.cv_builder.common.CommonConstrains.DB
import edu.miu.cs473de.cv_builder.database.AppDatabase

class CvBuilderApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DB= Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "cv_builder_db"
        ).build()
    }
}