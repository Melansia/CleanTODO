package com.example.todoappclean.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.todoappclean.R
import com.example.todoappclean.data.models.Priority
import com.example.todoappclean.fragments.SharedViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

//    private val mTodoViewModel: ToDoViewModel by viewModels()
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
        prioritiesSpinnerUpdate.setSelection(parsePriority(args.currentItem.priority))
        prioritiesSpinnerUpdate.onItemSelectedListener = mSharedViewModel.listener
        // Inflate the layout for this fragment
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    private fun parsePriority(priority: Priority):Int {
        return when(priority){
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }

}