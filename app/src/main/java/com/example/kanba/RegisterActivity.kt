package com.example.kanba

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var userDatabaseHelper: UserDatabaseHelper
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userDatabaseHelper = UserDatabaseHelper(this)

        // Vincular los elementos de la interfaz de usuario con las variables
        usernameEditText = findViewById(R.id.username_edit_text)
        passwordEditText = findViewById(R.id.password_edit_text)
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text)

        val registerButton: Button = findViewById(R.id.register_button)
        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (password == confirmPassword) {
                if (userDatabaseHelper.addUser(username, password)) {
                    Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                    finish() // Cerrar la actividad después de un registro exitoso
                } else {
                    Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }
}