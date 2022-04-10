package edu.miu.cs473de.cv_builder.database

import androidx.room.*
import edu.miu.cs473de.cv_builder.models.User

@Dao
interface UserDAO {

    @Query("SELECT user_id FROM user WHERE email LIKE :email AND password LIKE:pass LIMIT 1")
    suspend fun verifyUser(email:String,pass:String): User

    @Query("SELECT * FROM user WHERE user_id LIKE :userId LIMIT 1")
    suspend fun getUserById(userId:String):User


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(vararg user: User)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addResume(resume: Resume)
//
//    @Transaction
//    @Query("SELECT * FROM resume WHERE user_id Like :userId LIMIT 1")
//    suspend fun getResumeUsingUserId(userId:String):Resume

}