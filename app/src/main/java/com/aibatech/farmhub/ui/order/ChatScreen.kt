package com.aibatech.farmhub.ui.order

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatScreen(buyerId: String?) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Chat with $buyerId", style = MaterialTheme.typography.headlineMedium)

        // Chat messages (Replace with real data)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(listOf("Hello!", "Hi, how can I help?", "I want to buy your product.")) { message ->
                Text(text = message, modifier = Modifier.padding(8.dp))
            }
        }

        // Input Field
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = "",
                onValueChange = { /* Handle input */ },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type a message...") }
            )
            Button(onClick = { /* Send message */ }) {
                Text("Send")
            }
        }
    }
}
