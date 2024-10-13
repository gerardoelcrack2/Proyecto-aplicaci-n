package com.example.proyectoaplicacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.proyectoaplicacion.ui.Pantallas.Reproductor // Asegúrate de que esta línea sea correcta
import com.example.proyectoaplicacion.ui.theme.ProyectoAplicacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoAplicacionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Llamada a la pantalla del Reproductor
                    ReproductorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Función que contiene el diseño del reproductor
@Composable
fun ReproductorScreen(modifier: Modifier = Modifier) {
    Reproductor() // Llama a la función Reproductor desde el archivo Reproductor.kt
}
