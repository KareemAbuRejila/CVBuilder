package edu.miu.cs473de.cv_builder.models

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Education(
    @PrimaryKey(true)
    val id:Long,
    val imageUrl:Int,
    val school:String,
    val degree:String
)