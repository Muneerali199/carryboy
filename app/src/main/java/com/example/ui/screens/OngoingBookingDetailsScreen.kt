package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.PlayCircle
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
fun OngoingBookingDetailsScreen(onBack: () -> Unit, onStartWork: () -> Unit) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(Color.Black)
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onBack) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)
                }
                Text("Current Booking", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                IconButton(onClick = { /* TODO */ }) {
                    Icon(imageVector = Icons.Filled.Call, contentDescription = "Call Support", tint = Color.White)
                }
            }
        },
        containerColor = Surface
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text("Booking ID", fontSize = 12.sp, color = OnSurfaceVariant)
                    Text("#CHD12345", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = OnSurface)
                }
                Box(modifier = Modifier.background(TertiaryContainer.copy(alpha=0.2f), RoundedCornerShape(16.dp)).padding(horizontal = 12.dp, vertical = 4.dp)) {
                    Text("Ongoing", color = Tertiary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
            }
            
            Divider(color = SurfaceVariant)
            
            Column(modifier = Modifier.padding(20.dp).weight(1f)) {
                // Customer Profile
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(SurfaceContainerLowest)
                        .border(1.dp, SurfaceVariant, RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(48.dp).background(PrimaryFixed, CircleShape))
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("Customer", fontSize = 12.sp, color = OnSurfaceVariant)
                            Text("Aman Verma", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = OnSurface)
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        IconButton(onClick = {}, modifier = Modifier.background(SurfaceContainerLow, CircleShape).size(40.dp)) {
                            Icon(Icons.Filled.Call, contentDescription = null, tint = OnSurface)
                        }
                        IconButton(onClick = {}, modifier = Modifier.background(SurfaceContainerLow, CircleShape).size(40.dp)) {
                            Icon(Icons.Filled.Chat, contentDescription = null, tint = OnSurface)
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Map Snippet
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(128.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(SurfaceVariant)
                        .border(1.dp, SurfaceVariant, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Map View Placeholder", color = OnSurfaceVariant)
                    Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Surface, contentColor = OnSurface), elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)) {
                        Icon(Icons.Filled.Directions, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Tap to View Map", fontSize = 14.sp)
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Itinerary Location Timeline
                Column(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(SurfaceContainerLowest).border(1.dp, SurfaceVariant, RoundedCornerShape(16.dp)).padding(16.dp)) {
                    Row(verticalAlignment = Alignment.Top) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 8.dp)) {
                            Box(modifier = Modifier.size(16.dp).border(4.dp, TertiaryContainer, CircleShape))
                            Box(modifier = Modifier.width(2.dp).height(32.dp).background(SurfaceVariant))
                            Box(modifier = Modifier.size(16.dp).border(4.dp, ErrorColor, CircleShape))
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("Pickup Location", fontSize=12.sp, color=OnSurfaceVariant)
                            Text("Chandni Chowk, Delhi", fontSize=16.sp, fontWeight=FontWeight.SemiBold, color=OnSurface)
                            Text("Near Katra Neel", fontSize=14.sp, color=OnSurfaceVariant)
                            
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            Text("Work Location", fontSize=12.sp, color=OnSurfaceVariant)
                            Text("Nai Sarak, Delhi", fontSize=16.sp, fontWeight=FontWeight.SemiBold, color=OnSurface)
                            Text("Opp. Town Hall", fontSize=14.sp, color=OnSurfaceVariant)
                        }
                    }
                }
            }
            
            // Bottom Action 
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(SurfaceContainerLowest)
                    .padding(20.dp)
            ) {
                Text("Please reach pickup location to start", fontSize = 14.sp, color = OnSurfaceVariant, modifier = Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onStartWork,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SecondaryContainer, contentColor = OnSecondaryContainer),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Filled.PlayCircle, contentDescription = null, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Start Work", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
