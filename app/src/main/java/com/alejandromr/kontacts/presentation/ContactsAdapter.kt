package com.alejandromr.kontacts.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejandromr.kontacts.databinding.ContactItemViewBinding
import com.alejandromr.kontacts.domain.model.ContactModel

class ContactsAdapter(
    private val onItemClick: (contact: ContactModel) -> Unit,
    private val onItemDeleteClick: (contact: ContactModel) -> Unit
) :
    RecyclerView.Adapter<ContactViewHolder>() {

    private var items: Set<ContactModel> = emptySet()

    fun setItems(newItems: Set<ContactModel>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder =
        ContactViewHolder(
            ContactItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick,
            onItemDeleteClick
        )

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        items.elementAtOrNull(position)?.let {
            holder.update(it)
        }
    }

    override fun getItemCount(): Int = items.size

    fun getItem(position: Int) = items.elementAt(position)
}
