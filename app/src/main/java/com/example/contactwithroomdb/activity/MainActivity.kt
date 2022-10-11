package com.example.contactwithroomdb.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactwithroomdb.adapter.ContactAdapter
import com.example.contactwithroomdb.database.ContactDatabase
import com.example.contactwithroomdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var contactAdapter: ContactAdapter
    private val contactDatabase by lazy { ContactDatabase.getInstance(this) }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        contactAdapter = ContactAdapter(contactDatabase.dao().getAllContact())
        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = contactAdapter
        }

        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }
        contactAdapter.onItemClicked = {
            val bundle = bundleOf("contact" to it)
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}