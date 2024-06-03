package com.example.kanba

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var boardsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        boardsContainer = findViewById(R.id.boards_container)

        createExampleBoards()

        val createBoardButton: Button = findViewById(R.id.create_board_button)
        createBoardButton.setOnClickListener {
            // Aquí puedes abrir una nueva actividad para crear un tablero
            // val intent = Intent(this, CreateBoardActivity::class.java)
            // startActivity(intent)
        }
    }

    private fun createExampleBoards() {
        for (i in 1..3) {
            val boardButton = Button(this)
            boardButton.text = "Tablero $i"
            boardButton.setOnClickListener {
                if (i == 1) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            boardsContainer.addView(boardButton)
        }
    }
}

