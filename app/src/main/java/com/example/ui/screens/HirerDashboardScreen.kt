package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.User
import com.example.ui.theme.*
import org.osmdroid.util.GeoPoint
import kotlin.math.*

@Composable
fun HirerDashboardScreen(
    user: User?,
    onNavigateToProfile: () -> Unit,
    onNavigateToNewBooking: () -> Unit,
    onNavigateToMyBookings: () -> Unit,
    onLogout: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }

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
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Primary)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "CarryBoy",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Primary
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { }) {
                        Icon(Icons.Outlined.Notifications, contentDescription = "Alerts", tint = OnSurfaceVariant)
                    }
                    IconButton(onClick = onNavigateToProfile) {
                        Icon(Icons.Outlined.Person, contentDescription = "Profile", tint = OnSurfaceVariant)
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
                BottomNavItem(Icons.Filled.Home, "Home", selectedTab == 0) { selectedTab = 0 }
                BottomNavItem(Icons.Outlined.AddCircleOutline, "New Booking", selectedTab == 1) { selectedTab = 1; onNavigateToNewBooking() }
                BottomNavItem(Icons.Outlined.ListAlt, "My Bookings", selectedTab == 2) { selectedTab = 2; onNavigateToMyBookings() }
                BottomNavItem(Icons.Outlined.Person, "Profile", selectedTab == 3) { selectedTab = 3; onNavigateToProfile() }
            }
        },
        containerColor = Surface
    ) { innerPadding ->
        when (selectedTab) {
            0 -> HirerHomeTab(
                modifier = Modifier.padding(innerPadding),
                userName = user?.displayName ?: "User",
                onNewBooking = { selectedTab = 1; onNavigateToNewBooking() },
                onMyBookings = { selectedTab = 2; onNavigateToMyBookings() }
            )
            1 -> NewBookingTab(modifier = Modifier.padding(innerPadding))
            2 -> PlaceholderTab("My Bookings", innerPadding)
            3 -> PlaceholderTab("Profile", innerPadding)
        }
    }
}

@Composable
private fun HirerHomeTab(
    modifier: Modifier = Modifier,
    userName: String,
    onNewBooking: () -> Unit,
    onMyBookings: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            // Welcome Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SecondaryContainer)
                    .padding(24.dp)
            ) {
                Column {
                    Text(
                        "Hello, $userName!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnSecondaryContainer
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Need help with carrying items?",
                        fontSize = 16.sp,
                        color = OnSecondaryContainer.copy(alpha = 0.9f)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = onNewBooking,
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Surface,
                            contentColor = OnSurface
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Book a Worker", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        item {
            Text(
                "Quick Actions",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = OnSurface
            )
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                QuickActionCard(
                    icon = Icons.Filled.ShoppingCart,
                    label = "Shopping\nAssistance",
                    onClick = onNewBooking,
                    modifier = Modifier.weight(1f)
                )
                QuickActionCard(
                    icon = Icons.Filled.Moving,
                    label = "Moving\nHelp",
                    onClick = onNewBooking,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                QuickActionCard(
                    icon = Icons.Filled.Inventory,
                    label = "Market\nCarry",
                    onClick = onNewBooking,
                    modifier = Modifier.weight(1f)
                )
                QuickActionCard(
                    icon = Icons.Filled.MoreHoriz,
                    label = "Other\nTasks",
                    onClick = onNewBooking,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Recent Activity",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = OnSurface
            )
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SurfaceContainerLowest)
                    .border(1.dp, OutlineVariant.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Outlined.History,
                        contentDescription = null,
                        tint = OnSurfaceVariant,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "No bookings yet",
                        fontSize = 16.sp,
                        color = OnSurfaceVariant
                    )
                    Text(
                        "Book a worker to get started",
                        fontSize = 14.sp,
                        color = OnSurfaceVariant.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

@Composable
private fun NewBookingTab(modifier: Modifier = Modifier) {
    var locationQuery by remember { mutableStateOf("") }
    var targetLocation by remember { mutableStateOf<GeoPoint?>(null) }
    
    // Mock worker location near New Delhi
    val workerLocation = remember { GeoPoint(28.6139, 77.2090) }
    
    var distanceText by remember { mutableStateOf("") }

    fun calculateDistance(p1: GeoPoint, p2: GeoPoint): Double {
        val R = 6371.0 // Earth radius in km
        val dLat = Math.toRadians(p2.latitude - p1.latitude)
        val dLon = Math.toRadians(p2.longitude - p1.longitude)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(p1.latitude)) * cos(Math.toRadians(p2.latitude)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return R * c
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(SurfaceContainerHighest)
        ) {
            OsmMapView(
                modifier = Modifier.fillMaxSize(),
                targetLocation = targetLocation,
                workerLocation = workerLocation
            )
        }
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                "Find Worker",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = OnSurface
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedTextField(
                value = locationQuery,
                onValueChange = { locationQuery = it },
                label = { Text("Enter Pickup Coordinates (Lat, Lon) e.g., 28.5, 77.1") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Primary,
                    unfocusedBorderColor = OutlineVariant,
                    focusedContainerColor = SurfaceContainerLowest,
                    unfocusedContainerColor = SurfaceContainerLowest
                )
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = {
                    try {
                        val parts = locationQuery.split(",")
                        if (parts.size >= 2) {
                            val lat = parts[0].trim().toDouble()
                            val lon = parts[1].trim().toDouble()
                            val newTarget = GeoPoint(lat, lon)
                            targetLocation = newTarget
                            val dist = calculateDistance(newTarget, workerLocation)
                            distanceText = String.format("Worker is %.2f km away", dist)
                        } else {
                            distanceText = "Please enter valid 'Lat, Lon'"
                        }
                    } catch (e: Exception) {
                        distanceText = "Invalid coordinates format"
                    }
                },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Check Distance", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            
            if (distanceText.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    distanceText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Primary
                )
            }
        }
    }
}

@Composable
private fun QuickActionCard(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainerLowest)
            .border(1.dp, OutlineVariant.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(PrimaryFixed, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = Primary, modifier = Modifier.size(24.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = OnSurface,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
private fun PlaceholderTab(title: String, innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            title,
            fontSize = 18.sp,
            color = OnSurfaceVariant,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun BottomNavItem(icon: ImageVector, label: String, selected: Boolean, onClick: () -> Unit) {
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
        Text(label, fontSize = 11.sp, fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium, color = color)
    }
}
