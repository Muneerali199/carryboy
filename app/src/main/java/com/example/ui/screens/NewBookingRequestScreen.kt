package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun NewBookingRequestScreen(onBack: () -> Unit, onAccept: () -> Unit) {
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = OnSurface)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "New Booking Request",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnSurface
                    )
                }
            }
        },
        containerColor = Surface
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Surface)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(SurfaceContainerHighest),
                contentAlignment = Alignment.Center
            ) {
                // Map placeholder
                Text("Map Background", color = OnSurfaceVariant.copy(alpha = 0.5f))
                Box(
                    modifier = Modifier.fillMaxSize().background(
                        androidx.compose.ui.graphics.Brush.verticalGradient(
                            listOf(Color.Transparent, Surface.copy(alpha=0.8f))
                        )
                    )
                )
                Column(
                    modifier = Modifier.align(Alignment.BottomStart).padding(24.dp)
                ) {
                    Text("Chandni Chowk Area", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = OnSurface)
                    Text("0.6 km from your location", fontSize = 14.sp, color = OnSurfaceVariant)
                }
            }
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.background(TertiaryContainer.copy(alpha=0.2f), RoundedCornerShape(16.dp)).padding(horizontal=12.dp, vertical=4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(8.dp).background(Tertiary, CircleShape))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("New Booking", fontSize = 12.sp, color = Tertiary, fontWeight = FontWeight.Medium)
                    }
                    Text("1 Helper", fontSize=14.sp, color=OnSurfaceVariant)
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Text("₹149", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = OnSurface)
                        Text("Cash to be collected", fontSize = 14.sp, color = OnSurfaceVariant)
                    }
                    Text("1 Hour", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = OnSurface)
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                Divider(color = SurfaceContainerHighest)
                Spacer(modifier = Modifier.height(24.dp))

                Row(verticalAlignment = Alignment.Top) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 8.dp)) {
                        Box(modifier = Modifier.size(12.dp).border(2.dp, Tertiary, CircleShape))
                        Box(modifier = Modifier.width(2.dp).height(40.dp).background(OutlineVariant.copy(alpha=0.5f)))
                        Box(modifier = Modifier.size(12.dp).border(2.dp, ErrorColor, CircleShape))
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("PICKUP LOCATION", fontSize=12.sp, color=OnSurfaceVariant)
                        Text("Chandni Chowk, Delhi", fontSize=16.sp, fontWeight=FontWeight.Medium, color=OnSurface)
                        Text("Near Katra Neel", fontSize=14.sp, color=OnSurfaceVariant)
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        Text("DROP LOCATION", fontSize=12.sp, color=OnSurfaceVariant)
                        Text("Nai Sarak, Delhi", fontSize=16.sp, fontWeight=FontWeight.Medium, color=OnSurface)
                        Text("Opp. Town Hall", fontSize=14.sp, color=OnSurfaceVariant)
                    }
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text("Auto accept in", fontSize=14.sp, color=OnSurfaceVariant)
                        Text("00:15", fontSize=18.sp, fontWeight=FontWeight.Bold, color=OnSurface)
                    }
                    CircularProgressIndicator(progress = { 0.7f }, modifier = Modifier.size(40.dp), color = SecondaryContainer, trackColor = SurfaceContainerHighest)
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = onBack, modifier = Modifier.weight(1f).height(56.dp), colors = ButtonDefaults.buttonColors(containerColor = Surface, contentColor = OnSurface), border = androidx.compose.foundation.BorderStroke(1.dp, OutlineVariant), shape = RoundedCornerShape(12.dp)) {
                        Text("Reject", fontSize=18.sp, fontWeight=FontWeight.SemiBold)
                    }
                    Button(onClick = onAccept, modifier = Modifier.weight(2f).height(56.dp), colors = ButtonDefaults.buttonColors(containerColor = SecondaryContainer, contentColor = OnSecondaryContainer), shape = RoundedCornerShape(12.dp)) {
                        Text("Accept", fontSize=18.sp, fontWeight=FontWeight.Bold)
                    }
                }
            }
        }
    }
}
