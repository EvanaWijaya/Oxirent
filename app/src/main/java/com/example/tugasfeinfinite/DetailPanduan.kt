@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.tugasfeinfinite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DetailPanduan(
    tube: PanduanTabung,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = tube.name,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF00BCD4)
                )
            )
        },
        bottomBar = {}
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = tube.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = tube.imageRes),
                contentDescription = "Oxygen Cylinder",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))

            DescriptionBox(description = tube.description)
        }
    }
}


@Composable
fun DescriptionBox(description: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFF00BCD4), RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = """
                Deskripsi: Tipe A adalah tabung oksigen dengan kapasitas kecil, biasanya sekitar 0,5 hingga 1 liter. Bentuknya ringan dan mudah dibawa, sering digunakan untuk keperluan darurat atau pasien yang memerlukan oksigen dalam perjalanan.
                
                Kapasitas: 0,5 - 1 liter
                Tekanan Maksimum: 1500-2000 psi
                Material: Aluminium atau baja ringan
                
                Penggunaan: Ideal untuk penggunaan pribadi, pasien dengan mobilitas tinggi, atau untuk situasi darurat. Sering ditemukan dalam kit P3K atau tas medis darurat.
                
                Kelebihan: Ringan, mudah dibawa, dan praktis untuk penggunaan singkat.
                Kekurangan: Kapasitas terbatas, tidak cocok untuk penggunaan jangka panjang.
            """.trimIndent(),
            color = Color.White,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Left
        )
    }
}

data class PanduanTabung(
    val name: String,
    val description: String,
    val capacity: String,
    val imageRes: Int
)

