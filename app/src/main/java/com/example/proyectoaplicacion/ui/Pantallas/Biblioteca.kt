package com.example.proyectoaplicacion.ui.Pantallas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoaplicacion.BottomBarItem
import com.example.proyectoaplicacion.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BibliotecaScreen(navController: NavController) {
    val tabs = listOf("Home", "Library", "Search") // Cambia los nombres según necesites
    var selectedTabIndex = remember { 0 } // Inicialmente, selecciona el primer tab

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "Biblioteca",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Spacer(
                                modifier = Modifier
                                    .width(12.dp)
                                    .clickable { /* Acción al hacer clic */ })
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_settings_24),
                                contentDescription = "Settings",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.height(64.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    BottomBarItem(
                        iconRes = R.drawable.baseline_home_filled_24,
                        label = "Inicio",
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("main") }
                    )
                    BottomBarItem(
                        iconRes = R.drawable.outline_search_24,
                        label = "Buscar",
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("buscar") }
                    )
                    BottomBarItem(
                        iconRes = R.drawable.baseline_library_music_24,
                        label = "Biblioteca",
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("biblioteca") }
                    )
                    BottomBarItem(
                        iconRes = R.drawable.baseline_cloud_24,
                        label = "Suscripción",
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("suscripcion") }
                    )
                    BottomBarItem(
                        iconRes = R.drawable.baseline_account_circle_24,
                        label = "Perfil",
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("perfil") }
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Sección de la biblioteca
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    SectionItem(title = "Listas") { navController.navigate("listas") }
                    SectionItem(title = "Álbumes") { navController.navigate("albumes") }
                    SectionItem(title = "Siguiendo") { navController.navigate("siguiendo") }
                    SectionItem(title = "Emisoras") { navController.navigate("emisoras") }
                    SectionItem(title = "Tus estadísticas") { navController.navigate("estadisticas") }
                    SectionItem(title = "Tus subidas") { navController.navigate("subidas") }
                }
            }

            // Título de "Escuchado recientemente"
            item {
                Text(
                    text = "Escuchado recientemente",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(18.dp)
                )
            }

            // LazyRow dentro de un item del LazyColumn
            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(getRecentlyPlayedSongs()) { song ->
                        RecentlyPlayedItem(song)
                    }
                }
            }

            // Título del historial
            item {
                Text(
                    text = "Historial de reproducción",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(18.dp)
                )
            }

            // Items del historial
            items(getPlayHistory()) { song ->
                PlayHistoryItem(song)
            }
        }
    }
}

@Composable
fun SectionItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        Image(
            painter = painterResource(id = R.drawable.baseline_navigate_next_24), // Reemplaza con tu icono de flecha
            contentDescription = "Ir a $title",
            modifier = Modifier.size(24.dp), colorFilter = ColorFilter.tint(Color.Black)
        )
    }
}

@Composable
fun RecentlyPlayedItem(song: Song) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = song.albumCoverResId),
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = song.title,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 2.dp)
        )
        Text(
            text = song.artist,
            fontSize = 12.sp
        )
    }
}

@Composable
fun PlayHistoryItem(song: Song) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = song.albumCoverResId),
            contentDescription = null,
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(text = song.title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = song.artist, fontSize = 12.sp)
        }
    }
}

// Funciones para obtener datos simulados
fun getRecentlyPlayedSongs(): List<Song> {
    return listOf(
        Song("Duetos, vol. 2 - El Triste", "José José", R.drawable.image_recently_played_1),
        Song("Duetos, vol. 2 - Lo Pasado, Pasado", "José José", R.drawable.image_recently_played_2)
    )
}

fun getPlayHistory(): List<Song> {
    return listOf(
        Song("Duro a la baila - Volar y Volar", "Tropicalisimo Apache", R.drawable.image_recently_played_3),
        Song("Duro a la baila - Viento", "Tropicalisimo Apache", R.drawable.image_recently_played_4)
    )
}

// Data class Song
data class Song(val title: String, val artist: String, val albumCoverResId: Int)

