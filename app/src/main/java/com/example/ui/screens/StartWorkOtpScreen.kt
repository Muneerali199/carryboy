package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun StartWorkOtpScreen(onBack: () -> Unit, onStart: () -> Unit) {
    var otp1 by remember { mutableStateOf("1") }
    var otp2 by remember { mutableStateOf("2") }
    var otp3 by remember { mutableStateOf("3") }
    var otp4 by remember { mutableStateOf("4") }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(Surface)
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onBack) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = OnSurfaceVariant)
                }
                Text("Start Work (OTP)", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Primary)
                Spacer(modifier = Modifier.width(48.dp))
            }
        },
        containerColor = Surface
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            
            // Abstract Lock Placeholder
            Box(
                modifier = Modifier.size(192.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier.size(192.dp).background(PrimaryFixed.copy(alpha=0.2f), CircleShape))
                Icon(Icons.Filled.Verified, contentDescription = null, modifier = Modifier.size(128.dp), tint = PrimaryFixed)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text("Confirm OTP to start work", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = OnSurface)
            Text("Ask customer for the 4 digit OTP", fontSize = 16.sp, color = OnSurfaceVariant)
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                OtpBox(value = otp1, onValueChange = { otp1 = it })
                OtpBox(value = otp2, onValueChange = { otp2 = it })
                OtpBox(value = otp3, onValueChange = { otp3 = it })
                OtpBox(value = otp4, onValueChange = { otp4 = it })
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("OTP Matched!", fontSize = 16.sp, color = Tertiary, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = Tertiary)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier
                    .background(TertiaryContainer, RoundedCornerShape(24.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Verified, contentDescription = null, tint = OnTertiaryContainer, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("AADHAAR VERIFIED", fontSize = 12.sp, color = OnTertiaryContainer, fontWeight = FontWeight.Medium)
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Column(modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)) {
                Button(
                    onClick = onStart,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SecondaryContainer, contentColor = OnSecondaryContainer),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Filled.PlayCircle, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Start Work", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedButton(
                    onClick = onBack,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, OutlineVariant),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = OnSurface, containerColor = SurfaceContainerLow)
                ) {
                    Text("Cancel", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

@Composable
fun OtpBox(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.size(64.dp),
        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Primary,
            unfocusedBorderColor = OutlineVariant,
            focusedContainerColor = SurfaceContainerLowest,
            unfocusedContainerColor = SurfaceContainerLowest
        ),
        shape = RoundedCornerShape(12.dp)
    )
}
