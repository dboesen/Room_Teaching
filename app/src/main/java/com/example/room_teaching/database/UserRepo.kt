package com.example.room_teaching.database

import androidx.lifecycle.LiveData
import androidx.room.Dao


//abstract class that allows access to multiple data sources
// best practice for code separation and architecture

class UserRepo(private val userDao: dao) {

    val readAllTheData: LiveData<List<User>> = userDao.getAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.delUser(user)
    }
}