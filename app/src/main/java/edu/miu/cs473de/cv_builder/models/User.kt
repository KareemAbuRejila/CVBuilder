package edu.miu.cs473de.cv_builder.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user",
        foreignKeys = [
        androidx.room.ForeignKey(
            entity = Resume::class,
            parentColumns = ["id"],
            childColumns = ["resume_id"]
        )])
data class User(
    @PrimaryKey val id:Int,
    @ColumnInfo(name = "user_name") val userName:String?,
    @ColumnInfo(name = "email") val email:String?,
    @ColumnInfo(name = "password") val password:String?,
    @ColumnInfo(index = true) val resume_id: String
) {
}