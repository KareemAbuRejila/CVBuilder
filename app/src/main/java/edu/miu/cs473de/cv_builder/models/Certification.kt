package edu.miu.cs473de.cv_builder.models

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
data class Certification(
    @PrimaryKey(true)
    val id:Long,
    val imageUrl:Int,
    val name:String,
    val year:Int
)