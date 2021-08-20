package com.alejandromr.kontacts.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils.loadAnimation
import androidx.recyclerview.widget.RecyclerView
import com.alejandromr.kontacts.R
import com.alejandromr.kontacts.databinding.ContactItemViewBinding
import com.alejandromr.kontacts.domain.model.ContactModel

class ContactsAdapter(
    private val onItemClick: (contact: ContactModel) -> Unit
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
            onItemClick
        )

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.binding.itemListRoot.animation = loadAnimation(holder.itemView.context, R.anim.item_list_animation)
        items.elementAtOrNull(position)?.let {
            holder.update(it)
        }
    }

    override fun getItemCount(): Int = items.size

    fun getItem(position: Int) = items.elementAt(position)
}
