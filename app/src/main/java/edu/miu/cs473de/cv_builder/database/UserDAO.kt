package edu.miu.cs473de.cv_builder.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import edu.miu.cs473de.cv_builder.models.User

@Dao
interface UserDAO {

    @Query("SELECT * FROM user WHERE email LIKE :email AND password LIKE:pass LIMIT 1")
    fun verifyUser(email:String,pass:String):User

    @Insert
    fun addUser(vararg user: User)
    
}