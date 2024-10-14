package com.example.proyectoaplicacion.ui.Pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoaplicacion.R

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun Suscripcion(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "Suscripción",
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
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.height(64.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    com.example.proyectoaplicacion.BottomBarItem(
                        iconRes = R.drawable.baseline_home_filled_24,
                        label = "Inicio",
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("main") }
                    )
                    com.example.proyectoaplicacion.BottomBarItem(
                        iconRes = R.drawable.outline_search_24,
                        label = "Buscar",
                        modifier = Modifier.weight(1f),
                        onClick = {/*Poner pantalla de buscar*/}
                    )
                    com.example.proyectoaplicacion.BottomBarItem(
                        iconRes = R.drawable.baseline_library_music_24,
                        label = "Biblioteca",
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("biblioteca") }
                    )
                    com.example.proyectoaplicacion.BottomBarItem(
                        iconRes = R.drawable.baseline_cloud_24,
                        label = "Suscripción",
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("suscripcion") }
                    )
                    com.example.proyectoaplicacion.BottomBarItem(
                        iconRes = R.drawable.baseline_account_circle_24,
                        label = "Perfil",
                        modifier = Modifier.weight(1f),
                        onClick = {/*Poner pantalla de perfil*/}
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Primer Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column {
                    Text(
                        text = "¿Quieres seguir escuchando esta y muchas otras canciones más? ¡Suscríbete a nuestro servicio premium!",
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp)
                    )
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        painter = painterResource(id = R.drawable.marshmello_icon),
                        contentDescription = "Android Logo",
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Descubre una nueva dimensión en el streaming de música con el servicio de streaming más innovador del mercado.",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

            // Segundo Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column {
                    Text(
                        text = "¿Por qué elegir nuestro servicio?",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = "- Biblioteca musical extensa\n" +
                                "- Funciones únicas: Modo Slowed, Modo Reversa, Velocidad x2\n" +
                                "- Perfiles de artistas completos\n" +
                                "- Interfaz intuitiva",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(
                    text = "Planes de suscripción accesibles",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = "Disfruta de toda nuestra plataforma desde tan solo $00.00 pesos. ¡Sin publicidad y con acceso completo a todas las funciones premium!",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = "¿Listo para transformar tu forma de escuchar música?",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
            }
            // Card de botones
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Button(
                        onClick = { navController.navigate("activacion") },
                        modifier = Modifier.padding(top = 12.dp)
                    ) {
                        Text("Suscribirse ya por solo $00.00")
                    }
                    TextButton(
                        onClick = { navController.navigate("activacion") },
                        //modifier = Modifier.padding(top = 2.dp)
                    ) {
                        Text(
                            text = "Ahorra dinero con el plan familiar o de estudiante",
                            fontSize = 13.sp)
                    }
                }
            }
        }
    }
}


@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val isDarkTheme = isSystemInDarkTheme() // Detecta si el modo oscuro está activado

    val colorScheme = if (isDarkTheme) {
        darkColorScheme(
            primary = Color(0xFFBB86FC),
            primaryContainer = Color(0xFF121212),
            secondary = Color.Gray,
            background = Color(0xFF1E1F22),
            onPrimary = Color.White
        )
    } else {
        lightColorScheme(
            primary = MaterialTheme.colorScheme.primary,
            primaryContainer = MaterialTheme.colorScheme.primaryContainer,
            secondary = MaterialTheme.colorScheme.secondary,
            background = MaterialTheme.colorScheme.background,
            onPrimary = Color.Black
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

@Composable
fun BottomBarItem(iconRes: Int, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clickable { /* Acción al hacer clic */ },
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