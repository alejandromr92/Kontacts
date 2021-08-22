package com.alejandromr.kontacts.presentation.ui

import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import androidx.fragment.app.Fragment
import com.alejandromr.kontacts.R
import com.alejandromr.kontacts.databinding.FragmentContactDetailBinding
import com.alejandromr.kontacts.loadImage
import java.text.SimpleDateFormat
import java.util.*

class ContactDetailFragment : Fragment(R.layout.fragment_contact_detail) {

    private var binding: FragmentContactDetailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentContactDetailBinding.bind(view)

        arguments?.let { arguments ->
            ContactDetailFragmentArgs.fromBundle(arguments).contact.let { contact ->
                binding?.apply {
                    name.text =
                        getString(R.string.name_placeholder, contact.name.first, contact.name.last)

                    location.text = getString(
                        R.string.location_placeholder,
                        contact.location.street.name,
                        contact.location.street.number,
                        contact.location.city,
                        contact.location.state
                    )

                    email.text = contact.email

                    phone.text = contact.phone

                    gender.text = contact.gender

                    val format =
                        SimpleDateFormat(getString(R.string.date_pattern), Locale.getDefault())

                    format.parse(contact.registered.date)?.let {
                        registrationDate.text = DateUtils.formatDateTime(
                            context,
                            it.time,
                            DateUtils.FORMAT_ABBREV_MONTH
                        )
                    }

                    picture.loadImage(contact.picture.large)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}
