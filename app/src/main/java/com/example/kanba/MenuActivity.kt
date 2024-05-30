import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.kanba.MainActivity
import com.example.kanba.R

class MenuActivity : AppCompatActivity() {
    private lateinit var boardsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

       

        // Crear los tableros de ejemplo
        createExampleBoards()

        // Configurar el botón para crear un nuevo tablero
        val createBoardButton: Button = findViewById(R.id.create_board_button)
        createBoardButton.setOnClickListener {
            // Aquí puedes abrir una nueva actividad para crear un tablero
            // Por ejemplo:
            // val intent = Intent(this, CreateBoardActivity::class.java)
            // startActivity(intent)
        }
    }

    private fun createExampleBoards() {
        // Crear y agregar los tableros de ejemplo
        for (i in 1..3) {
            val boardButton = Button(this)
            boardButton.text = "Tablero $i"
            boardButton.setOnClickListener {
                if (i == 1) {
                    // Si es el primer tablero, volver al MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // Aquí puedes manejar la lógica para abrir otro tablero
                    // Por ejemplo:
                    // val intent = Intent(this, AnotherBoardActivity::class.java)
                    // startActivity(intent)
                }
            }
            boardsContainer.addView(boardButton)
        }
    }
}
