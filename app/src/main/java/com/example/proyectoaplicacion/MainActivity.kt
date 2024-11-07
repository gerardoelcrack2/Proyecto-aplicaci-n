package com.example.proyectoaplicacion

import android.os.Build
import android.os.Bundle
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController)
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
    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S // Comprueba si se puede usar color dinámico

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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.surface),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(360.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ray_volpe),
                                    contentDescription = "RAY VOLPE - SEE YOU DROP",
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(RoundedCornerShape(2.dp))
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    Text(
                                        text = "RAY VOLPE - SEE YOU DROP",
                                        color = MaterialTheme.colorScheme.primary,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = "RAY VOLPE \uD83E\uDD16, Monstercat, PhaseOne, Zomboy",
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontSize = 14.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }

                        item {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.marshmello_icon),
                                    contentDescription = "MARSHMELLO X RAY VOLPE - OLD SCHOOL",
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(RoundedCornerShape(2.dp))
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    Text(
                                        text = "MARSHMELLO X RAY VOLPE - OLD SCHOOL",
                                        color = MaterialTheme.colorScheme.primary,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = "RAY VOLPE \uD83E\uDD16 - Lista",
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontSize = 14.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
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