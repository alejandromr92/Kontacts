package com.alejandromr.kontacts.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejandromr.kontacts.databinding.ContactItemViewBinding
import com.alejandromr.kontacts.domain.ContactModel
import com.alejandromr.kontacts.domain.ResultsModel

class ContactsAdapter(private val onItemClick: (text: String) -> Unit) :
    RecyclerView.Adapter<ContactViewHolder>() {

    private var items: Set<ContactModel> = emptySet()

    fun setItems(newItems: ResultsModel) {
        items = newItems.results
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder =
        ContactViewHolder(
            ContactItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClick
        )

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        items.elementAtOrNull(position)?.let {
            holder.update(it)
        }
    }

    override fun getItemCount(): Int = items.size
}
