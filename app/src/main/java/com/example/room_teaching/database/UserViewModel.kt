package com.example.room_teaching.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// provide data to the UI and the communication between the Repo and said UI

class UserViewModel(application: Application): AndroidViewModel(application) {


    val readAllData: LiveData<List<User>>
    private val repo: UserRepo


    init {
        val ourDao = TheDatabase.getDatabase(application).userDAO()
        repo = UserRepo(ourDao)
        readAllData = repo.readAllTheData
    }

    fun addUser(user: User){
        // run this in a background thread or worker thread
        //bad to launch database form main thread - puts too much resources on main thread
        viewModelScope.launch(Dispatchers.IO){
            repo.addUser(user)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
           repo.updateUser(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteUser(user)
        }
    }
}