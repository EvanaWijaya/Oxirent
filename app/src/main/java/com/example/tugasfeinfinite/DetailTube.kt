@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
package com.example.tugasfeinfinite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(tube: Cylinder, onBackClick: () -> Unit) {
    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Tabung Oksigen DCY\nUkuran 40 X 10",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.tabung1), // Sesuaikan dengan resource Anda
                contentDescription = "Tabung Oksigen",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "Rp 50.000/hari",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Deskripsi",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Row pertama untuk Ukuran
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Jarak tetap antar elemen
                modifier = Modifier.fillMaxWidth()
            ) {
                DetailItem(
                    title = "Jenis",
                    value = "D-cylinder\nIndustri Kesehatan",
                    modifier = Modifier.weight(1f) // Membagi ruang sama besar
                )
                DetailItem(
                    title = "Ukuran",
                    value = "Tinggi 40-50 cm\nDiameter 10-15 cm",
                    modifier = Modifier.weight(1f)
                )
            }

            // Spacer untuk memberi jarak antara kotak "Ukuran" dan "Berat"
            Spacer(modifier = Modifier.height(16.dp))

            // Row kedua untuk Kapasitas dan Berat
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Jarak tetap antar elemen
                modifier = Modifier.fillMaxWidth()
            ) {
                DetailItem(
                    title = "Kapasitas",
                    value = "340-460 liter\nCocok Jangka Panjang",
                    modifier = Modifier.weight(1f)
                )
                DetailItem(
                    title = "Berat",
                    value = "3-4 kg\nMudah Dipindahkan",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(150.dp))
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BCD4))
            ) {
                Text(text = "Sewa Sekarang", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}


@Composable
fun DetailItem(title: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color(0xFFE3F2FD), RoundedCornerShape(8.dp))
            .padding(16.dp) // Padding lebih besar untuk mengisi ruang
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(
            text = value,
            fontSize = 12.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
