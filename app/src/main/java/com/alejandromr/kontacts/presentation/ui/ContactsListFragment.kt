package com.alejandromr.kontacts.presentation.ui

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alejandromr.kontacts.R
import com.alejandromr.kontacts.databinding.FragmentListBinding
import com.alejandromr.kontacts.domain.ContactModel
import com.alejandromr.kontacts.presentation.ContactsAdapter
import com.alejandromr.kontacts.presentation.Contract
import org.koin.android.ext.android.inject


class ContactsListFragment : Fragment(R.layout.fragment_list), Contract.View {

    private val presenter: Contract.Presenter by inject()

    private var binding: FragmentListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)

        binding?.let {
            configViews(it)
        }

        presenter.attachView(this)
        presenter.obtainContacts()
    }

    private fun configViews(binding: FragmentListBinding) {
        binding.modelList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = ContactsAdapter({ contact ->
                context?.let {
                    Toast.makeText(it, "CLICKED ${contact.name}", Toast.LENGTH_SHORT).show()
                }
                presenter.navigateToContactDetail(contact)
            }, { contact ->
                context?.let {
                    Toast.makeText(it, "DELETED ${contact.name}", Toast.LENGTH_SHORT).show()
                }
                presenter.deleteContact(contact)
            })
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        presenter.obtainContacts()
                    }
                }
            })
        }
    }

    private fun navigateToDetail() {
        val transaction = activity?.supportFragmentManager?.beginTransaction()

        transaction?.let {
            it.addToBackStack(ContactsListFragment.TAG)
            it.replace(R.id.contentLayout, FragmentDetail(), FragmentDetail.TAG)
            it.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.detachView()
        binding = null
    }

    override fun showProgress() {
        binding?.listProgressBar?.visibility = VISIBLE
    }

    override fun hideProgress() {
        binding?.listProgressBar?.visibility = GONE
    }

    override fun displayList(list: Set<ContactModel>) {
        (binding?.modelList?.adapter as? ContactsAdapter)?.setItems(list)
    }

    override fun displayError() {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle("Something went wrong")
                .setMessage("Would you like to try again?")
                .setPositiveButton(android.R.string.yes) { _, _ ->
                    presenter.obtainContacts()
                }  // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .show()
        }
    }

    companion object {
        const val TAG = "FragmentList"
    }
}
