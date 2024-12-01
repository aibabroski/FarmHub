package com.aibatech.farmhub.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aibatech.farmhub.ui.theme.poppins

@Composable
fun DashboardScreen(
    onAddProductClick: () -> Unit,
    onViewOrdersClick: () -> Unit,
    onProductClick: (String) -> Unit // Product ID
) {
    val products =
        remember { mutableStateListOf("Tomatoes", "Cucumbers", "Carrots") } // Sample product list

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Welcome Message
        Text(
            text = "Welcome to your Dashboard",
            fontFamily = poppins,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Add Product Button
        Button(
            onClick = { onAddProductClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add Product")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Add Product")
        }

        // Product List
        Text(
            text = "Your Products",
            fontFamily = poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn {
            items(products.size) { index ->
                ProductItem(
                    productName = products[index],
                    onClick = { onProductClick(products[index]) }
                )
            }
        }

        // Orders Overview
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Recent Orders",
            fontFamily = poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Button(
            onClick = { onViewOrdersClick() },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("View All Orders")
        }
    }
}

@Composable
fun ProductItem(productName: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = productName,
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
    }
}
