package com.aibatech.farmhub.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aibatech.farmhub.ui.theme.poppins

@Composable
fun DashboardScreen(
    onProductClick: (String) -> Unit,
    onOrderClick: (String) -> Unit,
) {
    val products = remember { mutableStateListOf("Tomatoes", "Cucumbers", "Carrots") } // Replace with real data
    val orders = remember { mutableStateListOf("Order 1", "Order 2", "Order 3") } // Replace with real data

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Welcome Text
        Text(
            text = "Welcome to your Dashboard",
            fontFamily = poppins,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Products Section
        Text(
            text = "Your Products",
            fontFamily = poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (products.isNotEmpty()) {
            LazyColumn {
                items(products.size) { index ->
                    ProductItem(
                        productName = products[index],
                        onClick = { onProductClick(products[index]) }
                    )
                }
            }
        } else {
            Text(
                text = "No products available. Add some products to start selling!",
                fontFamily = poppins,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Orders Section
        Text(
            text = "Recent Orders",
            fontFamily = poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (orders.isNotEmpty()) {
            LazyColumn {
                items(orders.size) { index ->
                    OrderItem(
                        orderName = orders[index],
                        onClick = { onOrderClick(orders[index]) }
                    )
                }
            }
        } else {
            Text(
                text = "No orders yet. You'll see your orders here when buyers place them.",
                fontFamily = poppins,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
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
        elevation = CardDefaults.cardElevation(4.dp)
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

@Composable
fun OrderItem(orderName: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = orderName,
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
    }
}
