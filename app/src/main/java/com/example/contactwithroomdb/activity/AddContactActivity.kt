package com.example.contactwithroomdb.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactwithroomdb.R
import com.example.contactwithroomdb.database.ContactDatabase
import com.example.contactwithroomdb.databinding.ActivityAddContactBinding
import com.example.contactwithroomdb.model.Contact

class AddContactActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddContactBinding.inflate(layoutInflater) }
    private val contactDatabase by lazy { ContactDatabase.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.add_contact)
        binding.btnSave.setOnClickListener {
            val name = binding.editName.text.toString().trim()
            val lastName = binding.editLastName.text.toString().trim()
            val number = binding.editNumber.text.toString().trim()
            val address = binding.editLocation.text.toString().trim()
            if (name.isNotEmpty() && address.isNotEmpty()) {
                contactDatabase.dao().saveContact(Contact(0, name, lastName, number, address))
                Toast.makeText(this, "Contact saved!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
            } else {
                Toast.makeText(this, "Enter data!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}