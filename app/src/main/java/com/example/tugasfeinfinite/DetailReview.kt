package com.example.tugasfeinfinite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ReviewDetailScreen(reviewText: String) {
    val displayedText = if (reviewText.isNotBlank()) {
        reviewText
    } else {
        "Tidak ada ulasan untuk ditampilkan."
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Gambar tabung oksigen
        Image(
            painter = painterResource(id = R.drawable.tabung1), // Ganti dengan gambar tabung Anda
            contentDescription = "Tabung Oksigen",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nama peminjam dan rating
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Evana Eka Wijaya",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )
            Row {
                repeat(5) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star Icon",
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Detail ulasan
        Text(
            text = reviewText,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewDetailScreenPreview() {
    ReviewDetailScreen(
        reviewText = "Tanggapan cepat dan sangat membantu! Tabung oksigen dalam kondisi baik dan siap pakai. Proses peminjaman mudah dan pelayanan sangat ramah. Terima kasih, sangat direkomendasikan!"
    )
}
