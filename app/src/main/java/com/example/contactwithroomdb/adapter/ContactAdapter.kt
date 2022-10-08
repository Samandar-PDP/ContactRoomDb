package com.example.contactwithroomdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactwithroomdb.databinding.ItemLayoutBinding
import com.example.contactwithroomdb.model.Contact

class ContactAdapter(private val contactList: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    lateinit var onItemClicked: (Contact) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount(): Int = contactList.size

    inner class ContactViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.apply {
                textTitle.text = "${contact.name[0]}"
                textName.text = "${contact.name} ${contact.lastName}"
                textNumber.text = contact.number
            }
            itemView.setOnClickListener {
                onItemClicked(contact)
            }
        }
    }
}