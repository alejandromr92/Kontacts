package com.alejandromr.kontacts.presentation

import androidx.recyclerview.widget.RecyclerView
import com.alejandromr.kontacts.databinding.ContactItemViewBinding
import com.alejandromr.kontacts.domain.ContactModel
import com.bumptech.glide.Glide

class ContactViewHolder(
    private val binding: ContactItemViewBinding,
    private val onClicked: (text: String) -> Unit
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

        binding.picture.apply {
            if (item.picture.thumbnail.isNotBlank()) {
                Glide.with(this.context)
                    .load(item.picture.medium)
                    .centerCrop().into(this)
            }
        }


        binding.itemListRoot.setOnClickListener {
//            onClicked.invoke("Item data is: ${item.data}")
        }
    }
}