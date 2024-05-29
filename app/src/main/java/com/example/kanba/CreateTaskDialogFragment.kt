package com.example.kanba

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class CreateTaskDialogFragment(private val listener: CreateTaskListener) : DialogFragment() {

    interface CreateTaskListener {
        fun onTaskCreated(taskName: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_create_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskNameEditText: EditText = view.findViewById(R.id.task_name_edit_text)
        val createButton: Button = view.findViewById(R.id.create_button)

        createButton.setOnClickListener {
            val taskName = taskNameEditText.text.toString()
            listener.onTaskCreated(taskName)
            dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is CreateTaskListener) {
            throw RuntimeException("$context must implement CreateTaskListener")
        }
    }
}

