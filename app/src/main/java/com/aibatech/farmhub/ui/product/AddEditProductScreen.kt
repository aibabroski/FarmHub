package com.aibatech.farmhub.ui.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
fun AddEditProductScreen() {
    var productName by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = productPrice,
            onValueChange = { productPrice = it },
            label = { Text("Product Price") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { /* Submit product to backend */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}
