package com.alejandromr.kontacts.presentation.ui

import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alejandromr.kontacts.R
import com.alejandromr.kontacts.databinding.FragmentListBinding
import com.alejandromr.kontacts.domain.model.ContactModel
import com.alejandromr.kontacts.presentation.ContactsAdapter
import com.alejandromr.kontacts.presentation.ContactsListContract
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import org.koin.android.ext.android.inject


class ContactsListFragment : Fragment(R.layout.fragment_list), ContactsListContract.View {

    private val presenter: ContactsListContract.Presenter by inject()

    private var binding: FragmentListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)

        binding?.let {
            configViews(it)
        }

        presenter.attachView(this)
        presenter.obtainContacts(false)
    }

    private fun configViews(binding: FragmentListBinding) {
        binding.modelList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = ContactsAdapter { contact ->
                navigateToContactDetail(contact)
            }
            val swipeToDelete = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    (adapter as? ContactsAdapter)?.getItem(viewHolder.adapterPosition)?.let {
                        presenter.deleteContact(it)
                    }
                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    context?.let {
                        RecyclerViewSwipeDecorator.Builder(
                            c,
                            recyclerView,
                            viewHolder,
                            dX,
                            dY,
                            actionState,
                            isCurrentlyActive
                        )
                            .addBackgroundColor(
                                ContextCompat.getColor(
                                    it,
                                    android.R.color.holo_red_light
                                )
                            )
                            .addActionIcon(R.drawable.ic_delete)
                            .create()
                            .decorate()
                    }
                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }
            }

            val itemTouchHelper = ItemTouchHelper(swipeToDelete)
            itemTouchHelper.attachToRecyclerView(this)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        presenter.obtainContacts(true)
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
                        queryHint = if (it.isEmpty()){
                            "Search a contact..."
                        } else {
                            ""
                        }
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
            MaterialAlertDialogBuilder(it)
                .setTitle("Something went wrong")
                .setMessage("Would you like to try again?")
                .setPositiveButton(android.R.string.yes) { _, _ ->
                    presenter.obtainContacts(false)
                }  // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .show()
        }
    }

    override fun displayErrorWhileDeleting(contact: ContactModel) {
        context?.let {
            MaterialAlertDialogBuilder(it)
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
