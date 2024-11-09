package com.example.proyectoaplicacion

import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.proyectoaplicacion.ui.Pantallas.Activacion
import com.example.proyectoaplicacion.ui.Pantallas.Albumes
import com.example.proyectoaplicacion.ui.Pantallas.BibliotecaScreen
import com.example.proyectoaplicacion.ui.Pantallas.Buscar
import com.example.proyectoaplicacion.ui.Pantallas.Emisoras
import com.example.proyectoaplicacion.ui.Pantallas.Estadisticas
import com.example.proyectoaplicacion.ui.Pantallas.Listas
import com.example.proyectoaplicacion.ui.Pantallas.Perfil
import com.example.proyectoaplicacion.ui.Pantallas.Siguiendo
import com.example.proyectoaplicacion.ui.Pantallas.Subidas
import com.example.proyectoaplicacion.ui.Pantallas.Suscripcion
import java.io.File
import java.io.IOException

class MainActivity : ComponentActivity() {
    private val STORAGE_PERMISSION_REQUEST_CODE =
        100 // Código para identificar la solicitud de permisos.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Verifica si la versión de Android es 13 o superior para solicitar el permiso de notificaciones.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions()
        }
        setContent {
            AppTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController)
            }
        }
    }

    private fun requestPermissions() {
        // Verifica y solicita el permiso de notificaciones para Android 13+ o maneja versiones anteriores.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Solicita el permiso de medios en Android 13 o superior (para archivos de audio)
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_MEDIA_AUDIO),
                STORAGE_PERMISSION_REQUEST_CODE
            )
        } else {
            // En versiones anteriores (antes de Android 13), solicita el permiso de almacenamiento
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Si no está concedido, solicita el permiso de almacenamiento
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    STORAGE_PERMISSION_REQUEST_CODE
                )
            }
        }
    }
}

@Composable
fun MainActivity(navController: NavHostController) {
    ScaffoldExample(navController)
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainActivity(navController)
        }
        composable("suscripcion") {
            Suscripcion(navController)
        }
        composable("activacion") {
            Activacion(navController)
        }
        composable("biblioteca") {
            BibliotecaScreen(navController)
        }
        composable("buscar") {
            Buscar(navController)
        }
        composable("perfil") {
            Perfil(navController)
        }
        composable("emisoras") {
            Emisoras(navController)
        }
        composable("siguiendo") {
            Siguiendo(navController)
        }
        composable("estadisticas") {
            Estadisticas(navController)
        }
        composable("listas") {
            Listas(navController)
        }
        composable("albumes") {
            Albumes(navController)
        }
        composable("subidas") {
            Subidas(navController)
        }
    }
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val darkTheme = isSystemInDarkTheme() // Detecta si el modo oscuro está activado
    val dynamicColor =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S // Comprueba si se puede usar color dinámico

    val colorScheme = when {
        dynamicColor && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        dynamicColor && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        else -> if (darkTheme) {
            MaterialTheme.colorScheme // Usa los colores predeterminados de MaterialTheme para modo oscuro
        } else {
            MaterialTheme.colorScheme // Usa los colores predeterminados de MaterialTheme para modo claro
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun ScaffoldExample(navController: NavHostController) {
    val context = LocalContext.current
    val audioFiles = remember { getAudioFiles(context) } // Obtén los archivos de audio
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "Música",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            /*Spacer(
                                modifier = Modifier
                                    .width(12.dp)
                                    .clickable { /* Acción al hacer clic */ })*/
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_settings_24),
                                contentDescription = "Settings",
                                //tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.CenterVertically)
                                    .clickable { /* Acción al hacer clic */ }
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
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.surface),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(audioFiles) { audioFile ->
                Row(
                    verticalAlignment = Alignment.CenterVertically, // Alineación vertical centrada
                    horizontalArrangement = Arrangement.Center, // Alineación horizontal centrada
                    modifier = Modifier.fillMaxWidth() // Asegura que ocupe todo el ancho
                ) {
                    // Usamos Glide para cargar la carátula
                    val albumArtUri = ContentUris.withAppendedId(
                        MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                        audioFile.albumId
                    )

                    val albumArtPainter = rememberAsyncImagePainter(
                        model = if (albumArtUri == Uri.EMPTY) {
                            // Si no hay carátula, usa la imagen genérica
                            R.drawable.note
                        } else {
                            albumArtUri
                        }
                    )

                    Image(
                        painter = albumArtPainter,
                        contentDescription = "Album Art",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(2.dp))
                    )

                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            modifier = Modifier.width(325.dp),
                            text = audioFile.name.substringBeforeLast("."), // Elimina la extensión
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    IconButton(onClick = { playAudio(audioFile.file) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_play_arrow_24),
                            contentDescription = "Play"
                        )
                    }
                }
            }
        }
    }
}

fun getAudioFiles(context: Context): List<AudioFile> {
    val audioFiles = mutableListOf<AudioFile>()
    val projection = arrayOf(
        MediaStore.Audio.Media.DATA,
        MediaStore.Audio.Media.DISPLAY_NAME,
        MediaStore.Audio.Media.ALBUM_ID // Añadimos el ALBUM_ID
    )

    val cursor = context.contentResolver.query(
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        projection,
        null,
        null,
        null
    )

    cursor?.use {
        val dataIndex = it.getColumnIndex(MediaStore.Audio.Media.DATA)
        val nameIndex = it.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)
        val albumIdIndex = it.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)

        while (it.moveToNext()) {
            val filePath = it.getString(dataIndex)
            val fileName = it.getString(nameIndex)
            val albumId = it.getLong(albumIdIndex)

            val file = File(filePath)
            audioFiles.add(AudioFile(file, fileName, albumId)) // Creamos un objeto con la información
        }
    }
    return audioFiles
}

data class AudioFile(val file: File, val name: String, val albumId: Long)

val mediaPlayer = MediaPlayer()

// Variable global para controlar la canción actual
var currentFile: File? = null

fun playAudio(file: File) {
    try {
        // Si la canción actual es diferente a la que se está pidiendo, cambia la canción
        if (currentFile != file) {
            // Si hay una canción en reproducción, deténla
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.reset() // Resetea el MediaPlayer
            }

            // Prepara el MediaPlayer con el nuevo archivo
            mediaPlayer.setDataSource(file.path)
            mediaPlayer.prepare()
            mediaPlayer.start() // Inicia la nueva canción
            currentFile = file // Actualiza la canción actual

            // Configura el listener para cuando la canción termine
            mediaPlayer.setOnCompletionListener {
                // Reinicia el reproductor para reproducir otra canción
                mediaPlayer.stop()
                mediaPlayer.reset()
                /*
                mediaPlayer.seekTo(0) // Reinicia la canción
                mediaPlayer.start() // Reproduce la canción desde el inicio
                */
                // O si quieres pasar a la siguiente canción, puedes llamar a otro método
                // playNextAudio()
            }
        } else {
            // Si la misma canción se está reproduciendo, alterna entre pausar y reanudar
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause() // Pausa la canción
            } else {
                mediaPlayer.start() // Reanuda la canción
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

@Composable
fun BottomBarItem(iconRes: Int, label: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally, // Alinea ícono y texto al centro
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp) // Tamaño del ícono
        )
        Spacer(modifier = Modifier.height(4.dp)) // Espacio entre ícono y texto
        Text(
            text = label,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 12.sp // Tamaño de texto ajustable
        )
    }
}