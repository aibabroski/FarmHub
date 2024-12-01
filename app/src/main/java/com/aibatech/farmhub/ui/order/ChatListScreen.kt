package com.aibatech.farmhub.ui.order

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatListScreen(onChatClick: (String) -> Unit) {
    val buyers = listOf("Buyer 1", "Buyer 2", "Buyer 3") // Replace with real data

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(buyers) { buyer ->
            ListItem(
                headlineContent = { Text(buyer) }, // Updated parameter name
                modifier = Modifier.clickable { onChatClick(buyer) }
            )
        }
    }
}
