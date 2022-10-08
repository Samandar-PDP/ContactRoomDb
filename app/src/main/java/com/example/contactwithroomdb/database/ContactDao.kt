package com.example.contactwithroomdb.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.contactwithroomdb.model.Contact

@Dao// Data access object
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveContact(contact: Contact)

    @Query("SELECT * FROM ContactTable")
    fun getAllContact(): List<Contact>
}