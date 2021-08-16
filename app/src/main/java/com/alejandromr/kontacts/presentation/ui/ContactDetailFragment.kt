package com.alejandromr.kontacts.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.alejandromr.kontacts.R
import com.alejandromr.kontacts.databinding.FragmentContactDetailBinding
import com.bumptech.glide.Glide

class ContactDetailFragment : Fragment(R.layout.fragment_contact_detail) {

    private var binding: FragmentContactDetailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentContactDetailBinding.bind(view)

        arguments?.let { arguments ->
            ContactDetailFragmentArgs.fromBundle(arguments).contact.let { contact ->
                binding?.apply {
                    name.text = "${contact.name.first} ${contact.name.last}"
                    location.text =
                        "${contact.location.street.name}, ${contact.location.street.number}, ${contact.location.city}, ${contact.location.state}"
                    email.text = "${contact.email} | ${contact.phone}"
                    registrationDate.text = "${contact.registered.date}"
                    picture.apply {
                        if (contact.picture.thumbnail.isNotBlank()) {
                            Glide.with(this.context)
                                .load(contact.picture.large)
                                .centerCrop().into(this)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }

    companion object {
        const val TAG = "ContactDetailFragment"
    }
}
