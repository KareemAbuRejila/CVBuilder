package edu.miu.cs473de.cv_builder.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.miu.cs473de.cv_builder.models.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao():UserDAO
}