package edu.miu.cs473de.cv_builder.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "resume",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"]
        )]
)
data class Resume(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(index = true) val user_id:String?

    )