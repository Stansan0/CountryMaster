package com.example.countrymaster

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class FondoMundo1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fondo_mundo1)

        // Obtener referencias de la UI
        val btnLogin = findViewById<ImageButton>(R.id.imageButton2)
        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etContraseña = findViewById<EditText>(R.id.etContraseña)

        // Instanciar el DAO de usuarios
        val userDAO = UserDAO(this)

        // 🔹 Asegurar que el usuario "admin" con clave "1234" siempre exista
        if (!userDAO.checkUser("admin", "1234")) {
            userDAO.insertUser("admin", "1234")
            Log.d("DB_TEST", "Usuario 'admin' creado para pruebas")
        }

        // 🔹 AGREGAR LISTENER AL BOTÓN DE LOGIN
        btnLogin.setOnClickListener {
            val usuario = etUsuario.text.toString().trim()
            val contraseña = etContraseña.text.toString().trim()

            if (userDAO.checkUser(usuario, contraseña)) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                // Ir a la siguiente pantalla (comentar hasta que esté lista)
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
