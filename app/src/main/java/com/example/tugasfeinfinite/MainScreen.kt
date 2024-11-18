@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class
)

package com.example.tugasfeinfinite

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            val currentRoute by navController.currentBackStackEntryAsState()
            val currentDestination = currentRoute?.destination?.route
            when (currentDestination) {
                "beranda" -> TopBar("Beranda")
                "tabung" -> TopBar("Pilih Tabung")
                "akun" -> TopBar("Profile")
                "detail_screen/{tubeId}" -> TopBar(
                    title = "Detail Produk",
                    showBackButton = true,
                    onBackClick = { navController.popBackStack() }
                )
                else -> {}
            }
        },
        bottomBar = {
            val currentRoute by navController.currentBackStackEntryAsState()
            val currentDestination = currentRoute?.destination?.route
            if (currentDestination != "detail_screen/{tubeId}") {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "beranda",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("beranda") { HomeScreen(navController) }
            composable("tabung") {
                TubeScreen { selectedCylinder ->
                    navController.navigate("detail_screen/${selectedCylinder.id}")
                }
            }
            composable("akun") { ProfileScreen() }
            composable(
                "detail/{name}",
                arguments = listOf(navArgument("name") { type = NavType.StringType })
            ) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: ""
                DetailPanduan(
                    tube = PanduanTabung(
                        name = name,
                        description = "Deskripsi untuk $name",
                        capacity = "0.5 - 1 liter",
                        imageRes = R.drawable.tabung1 // Gambar default
                    ),
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable(
                route = "reviewDetail/{userName}/{userRole}/{reviewText}/{rating}",
                arguments = listOf(
                    navArgument("userName") { type = NavType.StringType },
                    navArgument("userRole") { type = NavType.StringType },
                    navArgument("reviewText") { type = NavType.StringType },
                    navArgument("rating") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val userName = backStackEntry.arguments?.getString("userName") ?: ""
                val userRole = backStackEntry.arguments?.getString("userRole") ?: ""
                val reviewText = backStackEntry.arguments?.getString("reviewText") ?: ""
                val rating = backStackEntry.arguments?.getInt("rating") ?: 0

                ReviewCard(
                    userName = userName,
                    userRole = userRole,
                    reviewText = reviewText,
                    rating = rating,
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable("detail_screen/{tubeId}") { backStackEntry ->
                val tubeId = backStackEntry.arguments?.getString("tubeId")?.toIntOrNull()

                val cylinders = listOf(
                    Cylinder(1, "Tabung oksigen A-Cylinder FULL SET", 80000, "Kosong", R.drawable.tabung1),
                    Cylinder(2, "Tabung oksigen B-Cylinder", 50000, "Tersedia", R.drawable.tabung1),
                    Cylinder(3, "Tabung oksigen C-Cylinder FULL SET", 65000, "Tersedia", R.drawable.tabung1),
                    Cylinder(4, "Tabung oksigen D-Cylinder", 45000, "Tersedia", R.drawable.tabung1),
                    Cylinder(5, "Tabung oksigen E-Cylinder FULL SET", 50000, "Kosong", R.drawable.tabung1),
                    Cylinder(6, "Tabung oksigen F-Cylinder", 40000, "Tersedia", R.drawable.tabung1),
                    Cylinder(7, "Tabung oksigen G-Cylinder FULL SET", 60000, "Kosong", R.drawable.tabung1),
                    Cylinder(8, "Tabung oksigen H-Cylinder", 70000, "Tersedia", R.drawable.tabung1),
                    Cylinder(9, "Tabung oksigen I-Cylinder", 48000, "Tersedia", R.drawable.tabung1),
                    Cylinder(10, "Tabung oksigen J-Cylinder FULL SET", 45000, "Kosong", R.drawable.tabung1),
                )

                val tube = cylinders.firstOrNull { it.id == tubeId }
                if (tube != null) {
                    DetailScreen(
                        tube = tube,
                        onBackClick = { navController.popBackStack() }
                    )
                } else {
                    // Tampilkan pesan error atau kembalikan ke halaman sebelumnya
                    Text("Data tidak ditemukan")
                }

            }
        }
    }
}

@Composable
fun TopBar(
    title: String,
    showBackButton: Boolean = false,
    onBackClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { onBackClick?.invoke() ?: Log.w("TopBar", "onBackClick not provided") }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Kembali",
                        tint = Color.White
                    )
                }
            }
        },
        modifier = Modifier.statusBarsPadding(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF00BCD4)
        )
    )
}


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentRoute by navController.currentBackStackEntryAsState() // Mengamati rute saat ini
    val currentDestination = currentRoute?.destination?.route

    NavigationBar(
        containerColor = Color.White // Warna latar belakang BottomNavigation
    ) {
        val items = listOf(
            BottomNavItem("Beranda", "beranda", R.drawable.home),
            BottomNavItem("Tabung", "tabung", R.drawable.tube),
            BottomNavItem("Akun", "akun", R.drawable.orang)
        )
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label
                    )
                },
                label = { Text(text = item.label) },
                selected = currentDestination == item.route,
                onClick = {
                    if (currentDestination != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true // Menyimpan status navigasi
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF00BCD4), // Warna ikon yang aktif
                    selectedTextColor = Color(0xFF00BCD4), // Warna teks yang aktif
                    unselectedIconColor = Color.Gray,      // Warna ikon yang tidak aktif
                    unselectedTextColor = Color.Gray,      // Warna teks yang tidak aktif
                    indicatorColor = Color.Transparent     // Menghilangkan warna indikator
                )
            )
        }
    }
}

data class BottomNavItem(val label: String, val route: String, val icon: Int)
