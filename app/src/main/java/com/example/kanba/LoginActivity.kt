package com.example.kanba

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var userDatabaseHelper: UserDatabaseHelper
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userDatabaseHelper = UserDatabaseHelper(this)

        // Vincular los elementos de la interfaz de usuario con las variables
        usernameEditText = findViewById(R.id.username_edit_text)
        passwordEditText = findViewById(R.id.password_edit_text)

        val loginButton: Button = findViewById(R.id.login_button)
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (userDatabaseHelper.checkUser(username, password)) {
                // Iniciar la siguiente actividad después de iniciar sesión correctamente
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        val registerButton: Button = findViewById(R.id.register_button)
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
