package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
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
fun HelperDashboardScreen(
    onNavigateToEarnings: () -> Unit,
    onNavigateToNewRequest: () -> Unit,
    onNavigateToOngoing: () -> Unit,
) {
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
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu", tint = Primary)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "LaborConnect",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Primary
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Row(
                        modifier = Modifier
                            .background(TertiaryContainer.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                            .border(1.dp, TertiaryContainer.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(8.dp).background(Tertiary, CircleShape))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Online", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Tertiary)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "Alerts", tint = OnSurfaceVariant)
                    }
                }
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Surface)
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                BottomNavItem(icon = Icons.Filled.Home, label = "Home", selected = true, onClick = {})
                BottomNavItem(icon = Icons.Outlined.ListAlt, label = "Bookings", selected = false, onClick = onNavigateToOngoing)
                BottomNavItem(icon = Icons.Outlined.Verified, label = "Safety", selected = false, onClick = {})
                BottomNavItem(icon = Icons.Outlined.Person, label = "Profile", selected = false, onClick = {})
            }
        },
        containerColor = Surface
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(SecondaryContainer)
                        .padding(24.dp)
                ) {
                    Column {
                        Text("Good morning, Ramesh ✨", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = OnSecondaryContainer)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Let's get your first booking today.", fontSize = 16.sp, color = OnSecondaryContainer.copy(alpha = 0.9f))
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Surface.copy(alpha = 0.9f))
                                .clickable { onNavigateToEarnings() }
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("TODAY'S EARNINGS", fontSize = 12.sp, color = OnSurfaceVariant, letterSpacing = 0.5.sp)
                                Row(verticalAlignment = Alignment.Bottom) {
                                    Text("₹596", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = OnSurface)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Row(
                                        modifier = Modifier.background(TertiaryContainer.copy(alpha=0.2f), RoundedCornerShape(4.dp)).padding(horizontal=4.dp, vertical=2.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(Icons.Filled.TrendingUp, contentDescription = null, tint = Tertiary, modifier = Modifier.size(12.dp))
                                        Text("+12%", fontSize=12.sp, fontWeight = FontWeight.Medium, color = Tertiary)
                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("3 Bookings Completed", fontSize = 14.sp, color = OnSurfaceVariant)
                            }
                            Box(modifier = Modifier.size(40.dp).background(SurfaceContainerHigh, CircleShape), contentAlignment = Alignment.Center) {
                                Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = null, tint = Primary, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Surface)
                        .border(1.dp, OutlineVariant.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                        .clickable { onNavigateToNewRequest() }
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                        Box(modifier = Modifier.size(48.dp).background(PrimaryFixed, CircleShape), contentAlignment = Alignment.Center) {
                            Icon(Icons.Filled.CellTower, contentDescription = null, tint = Primary)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("Go Online to get more bookings", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = OnSurface)
                            Text("You will receive booking requests instantly.", fontSize = 14.sp, color = OnSurfaceVariant)
                        }
                    }
                    Switch(checked = true, onCheckedChange = {})
                }
            }

            item {
                Column {
                    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text("Available Requests near you", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = OnSurface)
                        Text("View Map", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Primary)
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Surface)
                            .border(1.dp, OutlineVariant.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                            .clickable { onNavigateToNewRequest() }
                    ) {
                        Column {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                                    Column {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text("NEW REQUEST", fontSize=12.sp, fontWeight=FontWeight.Bold, color=OnSecondaryContainer, modifier = Modifier.background(SecondaryContainer.copy(alpha=0.2f), RoundedCornerShape(4.dp)).padding(horizontal=8.dp, vertical=4.dp))
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Icon(Icons.Outlined.Schedule, contentDescription=null, tint=OnSurfaceVariant, modifier = Modifier.size(16.dp))
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text("1 Helper needed", fontSize=12.sp, color=OnSurfaceVariant)
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text("Carry Bags / Market Shopping", fontSize=20.sp, fontWeight=FontWeight.Bold, color=OnSurface)
                                    }
                                    Column(horizontalAlignment = Alignment.End) {
                                        Text("₹149", fontSize=20.sp, fontWeight = FontWeight.ExtraBold, color=OnSurface)
                                        Text("1 Hour • Cash", fontSize=12.sp, color=OnSurfaceVariant)
                                    }
                                }
                                
                                Spacer(modifier = Modifier.height(16.dp))
                                
                                Row(verticalAlignment = Alignment.Top) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 8.dp)) {
                                        Box(modifier = Modifier.size(12.dp).border(2.dp, Tertiary, CircleShape))
                                        Box(modifier = Modifier.width(2.dp).height(32.dp).background(OutlineVariant.copy(alpha=0.5f)))
                                        Box(modifier = Modifier.size(12.dp).border(2.dp, ErrorColor, CircleShape))
                                    }
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Column {
                                        Text("PICKUP LOCATION", fontSize=12.sp, color=OnSurfaceVariant)
                                        Text("Chandni Chowk, Delhi", fontSize=16.sp, fontWeight=FontWeight.Medium, color=OnSurface)
                                        Text("Near Katra Neel • 0.6 km away", fontSize=14.sp, color=OnSurfaceVariant)
                                        
                                        Spacer(modifier = Modifier.height(16.dp))
                                        
                                        Text("DROP LOCATION", fontSize=12.sp, color=OnSurfaceVariant)
                                        Text("Nai Sarak, Delhi", fontSize=16.sp, fontWeight=FontWeight.Medium, color=OnSurface)
                                        Text("Opp. Town Hall", fontSize=14.sp, color=OnSurfaceVariant)
                                    }
                                }
                                
                                Spacer(modifier = Modifier.height(16.dp))
                                
                                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                    Button(onClick = {}, modifier = Modifier.weight(1f).height(48.dp), colors = ButtonDefaults.buttonColors(containerColor = Surface, contentColor = OnSurface), border = androidx.compose.foundation.BorderStroke(1.dp, OutlineVariant), shape = RoundedCornerShape(12.dp)) {
                                        Text("Reject", fontSize=16.sp, fontWeight=FontWeight.SemiBold)
                                    }
                                    Button(onClick = { onNavigateToOngoing() }, modifier = Modifier.weight(2f).height(48.dp), colors = ButtonDefaults.buttonColors(containerColor = SecondaryContainer, contentColor = OnSecondaryContainer), shape = RoundedCornerShape(12.dp)) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text("Accept Request", fontSize=16.sp, fontWeight=FontWeight.Bold)
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Icon(Icons.Filled.CheckCircle, contentDescription=null, modifier = Modifier.size(20.dp))
                                        }
                                    }
                                }
                            }
                            
                            Box(modifier = Modifier.fillMaxWidth().height(4.dp).background(SurfaceContainerHigh)) {
                                Box(modifier = Modifier.fillMaxHeight().fillMaxWidth(0.7f).background(SecondaryContainer))
                            }
                            Text("Auto accept in 00:15", fontSize=10.sp, color=OnSurfaceVariant, modifier = Modifier.fillMaxWidth().background(SurfaceContainerLow).padding(vertical = 6.dp), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, selected: Boolean, onClick: () -> Unit) {
    val color = if (selected) Primary else OnSurfaceVariant
    val bgColor = if (selected) PrimaryFixed else Color.Transparent
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .background(bgColor)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = color, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 12.sp, fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium, color = color)
    }
}
