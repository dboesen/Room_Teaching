package com.example.room_teaching.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

//data access object - contain all methods for accessing the data

@Dao
interface dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Delete
    suspend fun delUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * from tbl_user ORDER BY id ASC")
    fun getAllData(): LiveData<List<User>>   // Flow or LiveData

}