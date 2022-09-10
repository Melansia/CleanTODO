package com.example.todoappclean.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoappclean.R
import com.example.todoappclean.data.models.Priority
import com.example.todoappclean.data.models.ToDoData
import com.example.todoappclean.data.viewmodel.ToDoViewModel
import com.example.todoappclean.fragments.SharedViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private val mTodoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private lateinit var etTitleUpdate: EditText
    private lateinit var prioritiesSpinnerUpdate: Spinner
    private lateinit var etDescriptionUpdate: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        etTitleUpdate = view.findViewById(R.id.etTitleUpdate)
        prioritiesSpinnerUpdate = view.findViewById(R.id.prioritiesSpinnerUpdate)
        etDescriptionUpdate = view.findViewById(R.id.etDescriptionUpdate)


        // Set Menu
        setHasOptionsMenu(true)

        etTitleUpdate.setText(args.currentItem.title)
        etDescriptionUpdate.setText(args.currentItem.descriptions)
        prioritiesSpinnerUpdate.setSelection(mSharedViewModel.parsePriorityToInt(args.currentItem.priority))
        prioritiesSpinnerUpdate.onItemSelectedListener = mSharedViewModel.listener
        // Inflate the layout for this fragment
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmItemRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        val title = etTitleUpdate.text.toString()
        val description = etDescriptionUpdate.text.toString()
        val getPriority = prioritiesSpinnerUpdate.selectedItem.toString()

        val validation = mSharedViewModel.verifyDataFromUser(title, description)

        if (validation) {
            // Update current item
            val updateItem = ToDoData(
                args.currentItem.id,
                title,
                mSharedViewModel.parsePriority(getPriority),
                description
            )
            mTodoViewModel.updateData(updateItem)
            Toast.makeText(requireContext(), "Successful Updated!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }
    }

    // Show AlertDialog to confirm delete
    private fun confirmItemRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mTodoViewModel.deleteItem(args.currentItem)
            Toast.makeText(
                requireContext(),
                "Successful Removed: '${args.currentItem.title}'",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete '${args.currentItem.title}'?")
        builder.setMessage("Are you sure you want to delete '${args.currentItem.title}'?")
        builder.create().show()
    }

}