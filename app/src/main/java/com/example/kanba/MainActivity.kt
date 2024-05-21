package com.example.kanba
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val tasksList = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Agregar algunas tareas de ejemplo
        tasksList.add(Task(text = "Tarea 1", state = "Pendiente"))
        tasksList.add(Task(text = "Tarea 2", state = "Completada"))

        // Mostrar las tareas en el contenedor
        val tasksContainer: LinearLayout = findViewById(R.id.tasks_container)
        for (task in tasksList) {
            addTaskToContainer(task, tasksContainer)
        }

        // Manejar clics en los botones de CRUD
        val createButton: Button = findViewById(R.id.create_button)
        val modifyButton: Button = findViewById(R.id.modify_button)
        val deleteButton: Button = findViewById(R.id.delete_button)

        createButton.setOnClickListener {
            // Lógica para crear una nueva tarea
            val newTask = Task(text = "Nueva Tarea", state = "Pendiente")
            tasksList.add(newTask)
            addTaskToContainer(newTask, tasksContainer)
        }

        modifyButton.setOnClickListener {
            // Verificar si hay alguna tarea seleccionada para modificar
            val selectedTask = tasksList.firstOrNull { it.state == "Seleccionada" }
            if (selectedTask != null) {
                // Modificar la tarea seleccionada
                // (Por ejemplo, abrir una actividad de edición pasando la tarea seleccionada)
                Toast.makeText(this, "Modificar tarea: ${selectedTask.text}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, seleccione una tarea para modificar", Toast.LENGTH_SHORT).show()
            }
        }

        deleteButton.setOnClickListener {
            // Verificar si hay alguna tarea seleccionada para eliminar
            val selectedTask = tasksList.firstOrNull { it.state == "Seleccionada" }
            if (selectedTask != null) {
                // Eliminar la tarea seleccionada
                tasksList.remove(selectedTask)
                tasksContainer.removeAllViews()
                for (task in tasksList) {
                    addTaskToContainer(task, tasksContainer)
                }
                Toast.makeText(this, "Tarea eliminada: ${selectedTask.text}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, seleccione una tarea para eliminar", Toast.LENGTH_SHORT).show()
            }
        }

        // Manejar clics en los botones de filtro
        val filterPendingButton: Button = findViewById(R.id.filter_pending_button)
        val filterInReviewButton: Button = findViewById(R.id.filter_in_review_button)
        val filterCompletedButton: Button = findViewById(R.id.filter_completed_button)
        val filterFinalizedButton: Button = findViewById(R.id.filter_finalized_button)

        filterPendingButton.setOnClickListener { filterTasks("Pendiente", tasksContainer) }
        filterInReviewButton.setOnClickListener { filterTasks("En Revisión", tasksContainer) }
        filterCompletedButton.setOnClickListener { filterTasks("Completada", tasksContainer) }
        filterFinalizedButton.setOnClickListener { filterTasks("Finalizado", tasksContainer) }
    }

    private fun addTaskToContainer(task: Task, container: LinearLayout) {
        val taskView = layoutInflater.inflate(R.layout.task_item, null)
        val taskTextView: TextView = taskView.findViewById(R.id.task_text_view)
        val changeStateButton: Button = taskView.findViewById(R.id.change_state_button)

        taskTextView.text = task.text
        updateButtonState(changeStateButton, task.state)

        changeStateButton.text = task.state
        changeStateButton.setOnClickListener {
            // Cambiar el estado de la tarea al hacer clic en el botón
            task.state = when (task.state) {
                "Pendiente" -> "En Revisión"
                "En Revisión" -> "Completada"
                "Completada" -> "Finalizado"
                else -> "Pendiente"
            }
            changeStateButton.text = task.state
            updateButtonState(changeStateButton, task.state)
        }

        container.addView(taskView)
    }

    private fun updateButtonState(button: Button, state: String) {
        val color = when (state) {
            "Pendiente" -> R.color.colorPending
            "En Revisión" -> R.color.colorInReview
            "Completada" -> R.color.colorCompleted
            "Finalizado" -> R.color.colorFinalized
            else -> R.color.colorPending
        }
        button.setBackgroundColor(ContextCompat.getColor(this, color))
    }

    private fun filterTasks(state: String, container: LinearLayout) {
        container.removeAllViews()
        val filteredTasks = tasksList.filter { it.state == state }
        for (task in filteredTasks) {
            addTaskToContainer(task, container)
        }
    }
}
