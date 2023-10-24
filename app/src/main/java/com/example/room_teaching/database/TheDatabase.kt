package com.example.room_teaching.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class TheDatabase: RoomDatabase() {

    abstract fun userDAO(): dao

    //what is visible to other classes
    companion object{

        //There will be only one instance of the database
        @Volatile  //rights to this field are shown to other threads
        private var OneInstance: TheDatabase? = null

        fun getDatabase(context: Context): TheDatabase{
            val testInstance = OneInstance
            //if there is existing - return that one
            if (testInstance != null){
                return testInstance
            }
            //if no instance create one
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TheDatabase::class.java,
                    "user_database"
                ).build()
                OneInstance = instance
                return instance
            }
        }
    }
}