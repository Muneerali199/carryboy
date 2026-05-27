package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun NavigateToPickupScreen(onBack: () -> Unit, onReached: () -> Unit) {
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
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = OnSurface)
                }
                Text("Navigate to Pickup", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = OnSurface)
                TextButton(onClick = { /* TODO */ }) {
                    Text("Support", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Primary)
                }
            }
        },
        containerColor = Surface
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Map Canvas Placeholder
            Box(modifier = Modifier.fillMaxSize().background(SurfaceContainerLowest)) {
                Text("Map Background", modifier = Modifier.align(Alignment.Center), color = OnSurfaceVariant)
            }
            
            // Location Info Card Top
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Surface)
                    .padding(16.dp)
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(verticalAlignment = Alignment.Top) {
                    Icon(Icons.Filled.MyLocation, contentDescription = null, tint = Tertiary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("Pickup Location", fontSize=12.sp, color=OnSurfaceVariant)
                        Text("Chandni Chowk, Delhi", fontSize=16.sp, fontWeight=FontWeight.SemiBold, color=OnSurface)
                        Text("Near Katra Neel", fontSize=14.sp, color=OnSurfaceVariant)
                    }
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Distance", fontSize=12.sp, color=OnSurfaceVariant)
                    Text("0.6 km", fontSize=16.sp, fontWeight=FontWeight.Bold, color=Primary)
                }
            }
            
            // Floating Action Area Bottom
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Surface)
                    .padding(24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceContainerLow)
                        .border(1.dp, SurfaceVariant, RoundedCornerShape(12.dp))
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Need help finding location?", fontSize = 12.sp, color = OnSurfaceVariant)
                        Text("Call Customer", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = OnSurface)
                    }
                    IconButton(onClick = {}, modifier = Modifier.background(PrimaryFixed, CircleShape)) {
                        Icon(Icons.Filled.Call, contentDescription = null, tint = OnPrimaryFixed)
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Button(
                    onClick = onReached,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SecondaryContainer, contentColor = OnSecondaryContainer),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Reached Pickup", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
