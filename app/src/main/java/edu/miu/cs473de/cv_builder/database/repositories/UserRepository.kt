package edu.miu.cs473de.cv_builder.database.repositories

import androidx.annotation.WorkerThread
import edu.miu.cs473de.cv_builder.database.UserDAO
import edu.miu.cs473de.cv_builder.models.User

class UserRepository(private val userDAO: UserDAO) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User){
        userDAO.addUser(user)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun verify(email:String,pass:String):User{
        return userDAO.verifyUser(email,pass)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getUserUsingID(userId:String):User{
        return userDAO.getUserById(userId)
    }
}