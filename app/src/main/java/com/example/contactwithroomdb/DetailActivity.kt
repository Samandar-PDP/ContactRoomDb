package com.example.contactwithroomdb

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.contactwithroomdb.databinding.ActivityDetailBinding
import com.example.contactwithroomdb.model.Contact
import kotlin.math.abs

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        val contact = intent.getParcelableExtra<Contact>("contact")!!

        binding.toolbarLayout.title = "${contact.name}${contact.lastName}"
        binding.toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        binding.toolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)

        binding.include.textNumber?.text = contact.number
        binding.include.textAddress?.text = contact.location

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}