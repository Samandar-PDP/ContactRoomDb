package com.example.contactwithroomdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactwithroomdb.model.Contact

// Room Database
@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun dao(): ContactDao

    companion object {
        private var instance: ContactDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ContactDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    ContactDatabase::class.java,
                    "Contact.Db"
                ).allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}