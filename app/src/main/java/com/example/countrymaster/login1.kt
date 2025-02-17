package com.example.countrymaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class login1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login1)

        // Configuración para aplicar los márgenes según el sistema de barras
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar el botón 'Iniciar sesión' con la acción de Intent
        val iniciarSesionButton = findViewById<Button>(R.id.buttonInicioSesion)
        iniciarSesionButton.setOnClickListener {
            // Iniciar la actividad FondoMundo1
            val intent = Intent(this, FondoMundo1::class.java)
            startActivity(intent)
        }
    }
}
