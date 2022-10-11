package com.example.contactwithroomdb.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.contactwithroomdb.R
import com.example.contactwithroomdb.database.ContactDatabase
import com.example.contactwithroomdb.databinding.ActivityEditBinding
import com.example.contactwithroomdb.model.Contact

class EditActivity : AppCompatActivity() {
    private val binding by lazy { ActivityEditBinding.inflate(layoutInflater) }
    private val contactDatabase by lazy { ContactDatabase.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val contact = intent.getParcelableExtra<Contact>("contact")

        contact?.let {
            supportActionBar?.title = "Edit: ${it.name}"
            binding.editName.setText(it.name)
            binding.editLastName.setText(it.lastName)
            binding.editNumber.setText(it.number)
            binding.editLocation.setText(it.location)
        }
        binding.btnCancel.setOnClickListener {
            finish()
        }
        binding.btnEdit.setOnClickListener {
            val name = binding.editName.text.toString().trim()
            val lastName = binding.editLastName.text.toString().trim()
            val number = binding.editNumber.text.toString().trim()
            val location = binding.editLocation.text.toString().trim()

            if (name.isNotBlank() && location.isNotBlank()) {
                contactDatabase.dao()
                    .updateContact(Contact(contact?.id!!, name, lastName, number, location))
                Toast.makeText(this, "Successfully updated!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()

            } else {
                Toast.makeText(this, "Enter data!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}