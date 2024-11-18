package com.example.tugasfeinfinite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.Text

@Composable
fun WelcomeVerdant() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background with gradient and image
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            // Gradasi Background Color
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF00897B),
                                Color(0xFF004D40)
                            )
                        )
                    )
            )

            Image(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        Image(
            painter = painterResource(id = R.drawable.logo_app), // Ganti dengan ID resource gambar Anda
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(50.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(), // Adjust top margin
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            // Logo Image
            Image(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = null,
                modifier = Modifier.size(90.dp, 97.dp)
            )

            Text(
                text = stringResource(id = R.string.app_name),
                style = TextStyle(
                    fontSize = 50.sp,
                    fontFamily = FontFamily(Font(R.font.averiaseriflibre_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            )

            Text(
                text = stringResource(id = R.string.subtitle),
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.averiaseriflibre_regular)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF), // Perbaikan penggunaan Color
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}


// Tambahkan Preview Annotations di bawah fungsi Composable
@Preview(showBackground = true, widthDp = 412, heightDp = 917)
@Composable
fun SplashScreenPreview() {
    WelcomeVerdant() // Tampilkan tampilan SplashScreen pada preview
}
