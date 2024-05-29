import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.kanba.R

class EditTaskDialogFragment(private val listener: EditTaskListener, private val currentTaskText: String) : DialogFragment() {

    interface EditTaskListener {
        fun onTaskEdited(newTaskText: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_edit_task, null)

            val taskEditText: EditText = view.findViewById(R.id.edit_task_name_edit_text)

            taskEditText.setText(currentTaskText)

            builder.setView(view)
                .setPositiveButton("Aceptar") { _, _ ->
                    val newTaskText = taskEditText.text.toString()
                    listener.onTaskEdited(newTaskText)
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.cancel()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}


