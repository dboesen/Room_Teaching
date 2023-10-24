package com.example.room_teaching.database


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize

//reps our table in the database

@Parcelize
@Entity(tableName = "tbl_user")
data class User (
    @PrimaryKey(autoGenerate = true)
    val ID: Int,
    val firstName: String,
    val lastName: String,
    val favColor: String,
    val heightIn: Int
): Parcelable