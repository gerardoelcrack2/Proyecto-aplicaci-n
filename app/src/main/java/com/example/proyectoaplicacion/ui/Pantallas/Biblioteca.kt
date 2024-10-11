import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectoaplicacion.R

class BibliotecaActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BibliotecaScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BibliotecaScreen() {
    val tabs = listOf("Home", "Library", "Search") // Cambia los nombres según necesites
    var selectedTabIndex = remember { 0 } // Inicialmente, selecciona el primer tab

    Column(modifier = Modifier.fillMaxSize()) {
        // Toolbar
        TopAppBar(
            title = { Text("Biblioteca", color = Color.White) },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary // Color de fondo de la barra
            )
        )

        // TabRow para la navegación superior
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }, // Cambia el tab seleccionado
                    text = { Text(tab, color = Color.White) }
                )
            }
        }

        // Contenido de la pantalla
        Column(modifier = Modifier.padding(16.dp)) {
            // Sección de la biblioteca
            SectionItem(title = "Listas") { /* Navegar a Listas */ }
            SectionItem(title = "Álbumes") { /* Navegar a Álbumes */ }
            SectionItem(title = "Siguiendo") { /* Navegar a Siguiendo */ }
            SectionItem(title = "Emisoras") { /* Navegar a Emisoras */ }
            SectionItem(title = "Tus estadísticas") { /* Navegar a Estadísticas */ }
            SectionItem(title = "Tus subidas") { /* Navegar a Subidas */ }
        }

        // Sección "Escuchado recientemente"
        Text(
            text = "Escuchado recientemente",
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )

        // LazyRow para mostrar las canciones recientes
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(getRecentlyPlayedSongs()) { song ->
                RecentlyPlayedItem(song)
            }
        }

        // Sección de historial
        Text(
            text = "Historial de reproducción",
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )

        // LazyColumn para mostrar el historial de reproducción
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
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
            painter = painterResource(id = R.drawable.baseline_arrow_circle_up_24), // Reemplaza con tu icono de flecha
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

// Preview para ver la pantalla en el editor
@Preview(showBackground = true)
@Composable
fun BibliotecaPreview() {
    BibliotecaScreen()
}