package com.example.tugasfeinfinite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import android.net.Uri
import androidx.compose.ui.graphics.Brush

val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_medium, FontWeight.Light)
)

fun navigateToReviewDetail(navController: NavHostController, reviewText: String) {
    navController.navigate("reviewDetail/${Uri.encode(reviewText)}")
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(8.dp))
        SelamatDatang()
        Spacer(modifier = Modifier.height(16.dp))
        BannerPembuka()
        Spacer(modifier = Modifier.height(16.dp))
        PilihanTabungSection(navController)
        Spacer(modifier = Modifier.height(16.dp))
        UlasanPeminjam(navController)  // Pastikan parameter yang benar digunakan di sini
    }
}

@Composable
fun SelamatDatang() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.orang),
                contentDescription = "User Profile",
                modifier = Modifier.fillMaxSize(),
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = "Hello!", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "Evana Eka Wijaya", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
    }
}

@Composable
fun BannerPembuka() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF00BCD4))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sewa Tabung Oksigen\nDengan Mudah",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = "Bisa pesan dimana saja. Diantar sampai rumah dan pastinya aman",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
            Image(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = "Gambar Tabung",
                modifier = Modifier
                    .size(150.dp)
            )
        }
    }
}


@Composable
fun PilihanTabungSection(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Header bagian atas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Panduan Tipe Tabung",
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp), // Spasi antar item
            contentPadding = PaddingValues(horizontal = 8.dp) // Padding kiri dan kanan
        ) {
            items(getTabungList()) { tabung ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(120.dp)
                        .clickable {
                            // Navigasi ke halaman detail dengan parameter nama tabung
                            navController.navigate("detail/${tabung.name}")
                        }
                ) {
                    // Gambar Tabung
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(2.dp, Color.Cyan, RoundedCornerShape(8.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = tabung.imageRes),
                            contentDescription = tabung.name,
                            modifier = Modifier.size(100.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Text(
                        text = tabung.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Poppins,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

data class Tabung(val name: String, val imageRes: Int)

fun getTabungList(): List<Tabung> {
    return listOf(
        Tabung("A-Cylinder", R.drawable.tabung1),
        Tabung("B-Cylinder", R.drawable.tabung1),
        Tabung("C-Cylinder", R.drawable.tabung1),
        Tabung("D-Cylinder", R.drawable.tabung1),
        Tabung("E-Cylinder", R.drawable.tabung1),
        Tabung("F-Cylinder", R.drawable.tabung1),
        Tabung("G-Cylinder", R.drawable.tabung1),
        Tabung("H-Cylinder", R.drawable.tabung1),
        Tabung("I-Cylinder", R.drawable.tabung1),
        Tabung("J-Cylinder", R.drawable.tabung1)
    )
}


// Data class untuk menyimpan informasi ulasan
data class Review(
    val userName: String,
    val userRole: String,
    val reviewText: String,
    val rating: Int
)

// Daftar ulasan untuk 10 peminjam
fun getReviews(): List<Review> {
    return listOf(
        Review("Evana Eka Wijaya", "Mahasiswa", "Tanggapan cepat dan sangat membantu! Tabung oksigen dalam kondisi baik dan siap pakai. Proses peminjaman mudah dan pelayanan sangat ramah. Terima kasih, sangat direkomendasikan!", 4),
        Review("Andi Satria", "Dokter", "Pelayanan cepat dan memuaskan. Tabung oksigen dalam kondisi prima. Sangat membantu dalam situasi darurat.", 5),
        Review("Budi Prasetyo", "Perawat", "Pengalaman yang sangat baik, tabung oksigen terjaga dengan baik dan tepat waktu pengirimannya. Sangat puas!", 5),
        Review("Rina Amalia", "Mahasiswa", "Pelayanan yang ramah dan tabung oksigen siap pakai. Saya sangat merekomendasikan untuk menggunakan layanan ini.", 4),
        Review("Hendra Santoso", "Karyawan", "Proses peminjaman sangat mudah dan cepat. Pelayanan pelanggan sangat baik, tabung oksigen dalam kondisi baik.", 4),
        Review("Siti Nurhaliza", "Mahasiswa", "Pelayanan sangat memuaskan dan tabung oksigen dikirim tepat waktu. Layanan sangat responsif. Terima kasih!", 5),
        Review("Rizki Hidayat", "Pengusaha", "Layanan peminjaman tabung oksigen sangat profesional dan memuaskan. Tabung dalam kondisi baik dan pelayanan pelanggan sangat responsif.", 5),
        Review("Joko Widodo", "Dokter", "Saya sangat puas dengan layanan ini. Tabung oksigen selalu siap pakai dan proses peminjaman sangat cepat.", 4),
        Review("Maya Sari", "Perawat", "Tabung oksigen yang dipinjam selalu dalam kondisi terbaik. Pengiriman cepat dan tepat waktu, sangat direkomendasikan!", 4),
        Review("Faisal Akbar", "Mahasiswa", "Sangat mudah untuk meminjam tabung oksigen di sini. Prosesnya cepat dan pelayanan sangat ramah.", 4)
    )
}

@Composable
fun UlasanPeminjam(navController: NavHostController) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Ulasan Peminjam",
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyColumn {
            items(getReviews()) { review ->
                ReviewItem(
                    userName = review.userName,
                    userRole = review.userRole,
                    reviewText = review.reviewText,
                    onClick = {
                        navController.navigate(
                            "reviewDetail/${Uri.encode(review.userName)}/${
                                Uri.encode(review.userRole)
                            }/${Uri.encode(review.reviewText)}/${review.rating}"
                        )
                    }
                )
            }
        }
    }
}



@Composable
fun ReviewItem(userName: String, userRole: String, reviewText: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp) // Jarak antar card
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp), // Sudut bulat modern
        elevation = CardDefaults.cardElevation(8.dp), // Efek bayangan lembut
        colors = CardDefaults.cardColors(
            containerColor = Color.White // Latar belakang putih bersih
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp) // Padding dalam card
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.Gray) // Profil gambar placeholder
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.orang),
                        contentDescription = "User Profile",
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp)) // Jarak antara foto profil dan teks
                Column {
                    Text(
                        text = userName,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Poppins,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = userRole,
                        fontSize = 12.sp,
                        fontFamily = Poppins,
                        color = Color.Gray
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp)) // Jarak antara header dan isi
            Text(
                text = reviewText,
                fontSize = 14.sp,
                fontFamily = Poppins,
                lineHeight = 20.sp,
                color = Color.DarkGray
            )
        }
    }
}




