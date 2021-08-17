package com.alejandromr.kontacts.presentation.ui

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alejandromr.kontacts.R
import com.alejandromr.kontacts.databinding.FragmentListBinding
import com.alejandromr.kontacts.domain.model.ContactModel
import com.alejandromr.kontacts.presentation.ContactsAdapter
import com.alejandromr.kontacts.presentation.ContactsListContract
import org.koin.android.ext.android.inject

class ContactsListFragment : Fragment(R.layout.fragment_list), ContactsListContract.View {

    private val presenter: ContactsListContract.Presenter by inject()

    private var binding: FragmentListBinding? = null

    private var shouldObtainInitialContacts = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)

        binding?.let {
            configViews(it)
        }

        presenter.attachView(this)
        if (shouldObtainInitialContacts) {
            presenter.obtainContacts()
            shouldObtainInitialContacts = false
        }
    }

    private fun configViews(binding: FragmentListBinding) {
        binding.modelList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = ContactsAdapter({ contact ->
                navigateToContactDetail(contact)
            }, { contact ->
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

        binding.searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        presenter.filterByInput(newText)
                    }
                    return false
                }
            })
        }
    }

    private fun navigateToContactDetail(contact: ContactModel) {
        view?.let {
            Navigation.findNavController(it)
                .navigate(ContactsListFragmentDirections.navigateToDetail(contact))
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

    override fun displayErrorWhileDeleting(contact: ContactModel) {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle("Something went wrong while trying to delete ${contact.name.first}")
                .setMessage("Would you like to try again?")
                .setPositiveButton(android.R.string.yes) { _, _ ->
                    presenter.deleteContact(contact)
                }  // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .show()
        }
    }

    companion object {
        const val TAG = "ContactsListFragment"
    }
}
