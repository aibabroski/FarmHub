package com.aibatech.farmhub.ui.order

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OrderDetailsScreen(orderId: String) {
    Text(text = "Order Details for $orderId", modifier = Modifier.fillMaxSize())
}
