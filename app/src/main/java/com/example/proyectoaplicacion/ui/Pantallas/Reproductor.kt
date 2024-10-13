package com.example.proyectoaplicacion.ui.Pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.proyectoaplicacion.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Reproductor() {
    var isPlaying by remember { mutableStateOf(false) }
    var playbackSpeed by remember { mutableStateOf(1f) }
    var currentProgress by remember { mutableStateOf(0f) } // Progreso de la canción
    val totalDuration = 100f // Duración total de la canción

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.jose40y20),
            contentDescription = "Portada del Álbum",
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "40 y 20", style = MaterialTheme.typography.headlineMedium)
        Text(text = "José José", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        //Barra de progreso
        LinearProgressIndicator(
            progress = currentProgress / totalDuration,
            modifier = Modifier.fillMaxWidth().height(4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botones de reproducción
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            IconButton(onClick = { /* Aleatorio */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_shuffle), // Asegúrate de que tienes el ícono para aleatorio
                    contentDescription = "Aleatorio",
                    tint = Color.Unspecified
                )
            }
            IconButton(onClick = { /* Reversa */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_rewind),
                    contentDescription = "Reversa",
                    tint = Color.Unspecified
                )
            }
            IconButton(onClick = { isPlaying = !isPlaying }) {
                Icon(
                    painter = painterResource(id = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play),
                    contentDescription = if (isPlaying) "Pausar" else "Reproducir",
                    tint = Color.Unspecified
                )
            }
            IconButton(onClick = { /* Adelantar */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_forward),
                    contentDescription = "Adelantar",
                    tint = Color.Unspecified
                )
            }
            IconButton(onClick = { /* Repetir */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_repeat), // Asegúrate de que tienes el ícono para repetir
                    contentDescription = "Repetir",
                    tint = Color.Unspecified
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Ajuste de velocidad
        Text(text = "Velocidad de reproducción: ${playbackSpeed}x")
        Slider(
            value = playbackSpeed,
            onValueChange = { playbackSpeed = it },
            valueRange = 0.5f..2f,
            steps = 3
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReproductorPreview() {
    Reproductor()
}
