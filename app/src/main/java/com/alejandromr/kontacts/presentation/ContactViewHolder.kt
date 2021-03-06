package com.alejandromr.kontacts.presentation

import androidx.recyclerview.widget.RecyclerView
import com.alejandromr.kontacts.databinding.ContactItemViewBinding
import com.alejandromr.kontacts.domain.model.ContactModel
import com.alejandromr.kontacts.loadImage

class ContactViewHolder(
    val binding: ContactItemViewBinding,
    private val onContactClicked: (contact: ContactModel) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun update(item: ContactModel) {
        binding.name.apply {
            text = "${item.name.first} ${item.name.last}"
        }

        binding.email.apply {
            text = item.email
        }

        binding.phone.apply {
            text = item.phone
        }

        binding.picture.loadImage(item.picture.large)

        binding.itemListRoot.setOnClickListener {
            onContactClicked.invoke(item)
        }
    }
}
