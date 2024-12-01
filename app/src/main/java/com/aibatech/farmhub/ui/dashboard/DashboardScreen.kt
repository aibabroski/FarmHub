package com.aibatech.farmhub.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aibatech.farmhub.ui.theme.poppins

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Summary Section
        SummarySection()

        Spacer(modifier = Modifier.height(16.dp))

        // Orders Section
        OrdersSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Products Section
        ProductsSection()
    }
}

@Composable
fun SummarySection() {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Total Products
            SummaryItem(title = "Products", value = "15;")

            // Total Orders
            SummaryItem(title = "Orders", value = "23")

            // Revenue
            SummaryItem(title = "Revenue", value = "$1,500")
        }
    }
}

@Composable
fun SummaryItem(title: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppins
        )
        Text(
            text = title,
            fontSize = 14.sp,
            fontFamily = poppins
        )
    }
}

@Composable
fun OrdersSection() {
    Text(
        text = "Recent Orders",
        style = MaterialTheme.typography.headlineMedium.copy(fontFamily = poppins),
        modifier = Modifier.padding(bottom = 8.dp)
    )

    LazyColumn(modifier = Modifier.height(150.dp)) {
        items((1..5).toList()) { order ->
            OrderCard(
                buyerName = "Buyer $order",
                orderTotal = "$${order * 50}",
                status = if (order % 2 == 0) "Completed" else "Pending"
            )
        }
    }
}

@Composable
fun OrderCard(buyerName: String, orderTotal: String, status: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = buyerName,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppins
                )
                Text(
                    text = orderTotal,
                    fontSize = 14.sp,
                    fontFamily = poppins
                )
            }
            Text(
                text = status,
                fontFamily = poppins,
                color = if (status == "Completed") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun ProductsSection() {
    Text(
        text = "Your Products",
        style = MaterialTheme.typography.headlineMedium.copy(fontFamily = poppins),
        modifier = Modifier.padding(bottom = 8.dp)
    )

    LazyColumn {
        items((1..10).toList()) { product ->
            ProductCard(productName = "Product $product", price = "$${product * 10}")
        }
    }
}

@Composable
fun ProductCard(productName: String, price: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = productName,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppins
                )
                Text(
                    text = price,
                    fontSize = 14.sp,
                    fontFamily = poppins
                )
            }
            Button(onClick = { /* Navigate to edit screen */ }) {
                Text(
                    text = "Edit",
                    fontFamily = poppins
                )
            }
        }
    }
}
