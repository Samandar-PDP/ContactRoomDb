package com.example.contactwithroomdb.database

import androidx.room.*
import com.example.contactwithroomdb.model.Contact

@Dao// Data access object
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)

    @Query("SELECT * FROM ContactTable WHERE id = :id")
    fun getContactById(id: Int): Contact

    @Update
    fun updateContact(contact: Contact)

    @Query("SELECT * FROM ContactTable")
    fun getAllContact(): List<Contact>
}