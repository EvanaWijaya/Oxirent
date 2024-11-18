package com.example.tugasfeinfinite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TubeScreen(onCylinderClick: (Cylinder) -> Unit) {
    var selectedFilter by remember { mutableStateOf("Semua") }
    val filters = listOf(
        "Semua", "A-Cylinder", "B-Cylinder", "C-Cylinder", "D-Cylinder",
        "E-Cylinder", "F-Cylinder", "G-Cylinder", "H-Cylinder", "I-Cylinder", "J-Cylinder"
    )
    var expanded by remember { mutableStateOf(false) }
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Dropdown Filter
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedFilter,
                onValueChange = { },
                readOnly = true,
                label = { Text("Pilih Tipe") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.LightGray,
                    focusedIndicatorColor = Color(0xFF00BCD4),
                    unfocusedIndicatorColor = Color(0xFF00BCD4),
                    focusedLabelColor = Color(0xFF00BCD4),
                    cursorColor = Color(0xFF00BCD4)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                filters.forEach { filter ->
                    DropdownMenuItem(
                        text = { Text(filter) },
                        onClick = {
                            selectedFilter = filter
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(cylinders.filter {
                it.name.contains(selectedFilter, true) || selectedFilter == "Semua"
            }) { cylinder ->
                OxygenCylinderItem(cylinder) {
                    onCylinderClick(cylinder)
                }
            }
        }
    }
}

@Composable
fun OxygenCylinderItem(cylinder: Cylinder, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = cylinder.image),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = cylinder.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp),
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Rp ${cylinder.price}/hari",
                color = Color.Gray,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Status: ${cylinder.status}",
                color = if (cylinder.status == "Tersedia") Color.Green else Color.Red,
                fontSize = 12.sp
            )
        }
    }
}



data class Cylinder(
    val id: Int,
    val name: String,
    val price: Int,
    val status: String,
    val image: Int
)
