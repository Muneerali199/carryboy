package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.User
import com.example.ui.theme.*

@Composable
fun AadharVerificationScreen(
    user: User,
    onVerify: (User) -> Unit,
    onLogout: () -> Unit
) {
    var aadhar by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    var isVerifying by remember { mutableStateOf(false) }

    Scaffold(containerColor = Surface) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Filled.Badge,
                contentDescription = null,
                tint = Primary,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                "Aadhar Verification",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = OnSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "As a worker, you must verify your identity to start accepting jobs.",
                fontSize = 14.sp,
                color = OnSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = aadhar,
                onValueChange = { 
                    if (it.length <= 12 && it.all { char -> char.isDigit() }) {
                        aadhar = it
                        error = null
                    }
                },
                label = { Text("12-Digit Aadhar Number") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            if (error != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = error!!,
                    color = ErrorColor,
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (aadhar.length != 12) {
                        error = "Aadhar number must be exactly 12 digits"
                    } else {
                        isVerifying = true
                        // Simulate API verification delay
                        val updatedUser = user.copy(
                            isAadharVerified = true,
                            aadharNumber = aadhar
                        )
                        onVerify(updatedUser)
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = aadhar.length == 12 && !isVerifying,
                colors = ButtonDefaults.buttonColors(containerColor = Primary, contentColor = OnSecondaryContainer),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isVerifying) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = OnSecondaryContainer)
                } else {
                    Text("Verify Aadhar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            TextButton(onClick = onLogout) {
                Text("Logout", color = Primary)
            }
        }
    }
}
