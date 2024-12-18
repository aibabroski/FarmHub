package com.aibatech.farmhub.ui.product

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProductDetailsScreen(productId: String) {
    Text(text = "Product Details for $productId", modifier = Modifier.fillMaxSize())
}
