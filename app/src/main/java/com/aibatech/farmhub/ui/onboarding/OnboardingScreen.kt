package com.aibatech.farmhub.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aibatech.farmhub.R
import com.aibatech.farmhub.ui.theme.poppins

@Composable
fun OnboardingScreen(onGetStartedClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.ic_delivery_guy), // Replace with your image resource
            contentDescription = "Delivery Man",
            modifier = Modifier.fillMaxSize()
        )

        // Gradient overlay (for text visibility)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
                            startY = size.height / 2,
                            endY = size.height
                        )
                    )
                }
        )

        // Text and Button Overlay
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1.4f)) // Push the content up
            Column(
                modifier = Modifier.padding(bottom = 100.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title Text
                Text(
                    text = "Welcome\nto our FarmHub",
                    fontFamily = poppins,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 50.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Subtitle Text
                Text(
                    text = "Sell your groceries in one tap",
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Get Started Button
                Button(
                    onClick = { onGetStartedClick(

                    ) },
                    modifier = Modifier.fillMaxWidth(0.8f),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Get Started",
                        fontFamily = poppins,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp)
                }
            }
        }
    }
}