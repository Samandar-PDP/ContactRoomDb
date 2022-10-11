package com.example.contactwithroomdb.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.contactwithroomdb.R
import com.example.contactwithroomdb.database.ContactDatabase
import com.example.contactwithroomdb.databinding.ActivityDetailBinding
import com.example.contactwithroomdb.model.Contact

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val contactDatabase by lazy { ContactDatabase.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        val contact = intent.getParcelableExtra<Contact>("contact")!!

        binding.toolbarLayout.title = "${contact.name} ${contact.lastName}"
        binding.toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        binding.toolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)

        binding.include.textNumber?.text = contact.number
        binding.include.textAddress?.text = contact.location

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        binding.btnEdit.setOnClickListener {
            val bundle = bundleOf("contact" to contact)
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        binding.btnDelete.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("Delete")
                setIcon(R.drawable.ic_baseline_delete_outline_24)
                setMessage("Do you want to delete this contact?")
                setPositiveButton("Yes") { di, _ ->
                    contactDatabase.dao().deleteContact(contact)
                    startActivity(Intent(this@DetailActivity, MainActivity::class.java))
                    finishAffinity()
                    Toast.makeText(this@DetailActivity, "Contact deleted", Toast.LENGTH_SHORT)
                        .show()
                }
                setNegativeButton("Cancel", null)
            }.create().show()
        }
    }
}