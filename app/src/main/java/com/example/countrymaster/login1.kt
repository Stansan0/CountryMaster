package com.example.countrymaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class login1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login1)
        val buttonPrincipal = findViewById<Button>(R.id.buttonPrincipal)
        Toast.makeText(this,"onCreate", Toast.LENGTH_SHORT).show();
        buttonPrincipal.setOnClickListener {
            startActivity(Intent(this,FondoMundo1::class.java))
        }
    }
}