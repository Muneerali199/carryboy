package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun EarningsOverviewScreen(onBack: () -> Unit) {
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
                    Text("Earnings", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = OnSurface)
                }
                Box(modifier = Modifier.size(40.dp).background(TertiaryContainer, CircleShape).border(1.dp, OutlineVariant, CircleShape))
            }
        },
        containerColor = Surface
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(SecondaryContainer)
                        .padding(24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Total Earnings", fontSize = 12.sp, color = OnSecondaryContainer)
                        Text("₹2,856", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = OnSecondaryContainer)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("This Month", fontSize = 12.sp, color = OnSecondaryContainer)
                            Icon(Icons.Filled.ExpandMore, contentDescription = null, tint = OnSecondaryContainer, modifier = Modifier.size(16.dp))
                        }
                    }
                    Box(
                        modifier = Modifier.size(64.dp).background(SecondaryContainer.copy(alpha=0.5f), CircleShape).border(1.dp, SecondaryContainer, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Filled.AccountBalanceWallet, contentDescription = null, tint = OnSecondaryContainer, modifier = Modifier.size(36.dp))
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
            
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(16.dp))
                            .background(SurfaceContainerLowest)
                            .border(1.dp, OutlineVariant.copy(alpha=0.4f), RoundedCornerShape(16.dp))
                            .padding(16.dp)
                    ) {
                        Text("This Week", fontSize = 12.sp, color = OnSurfaceVariant)
                        Text("₹1,256", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = OnSurface)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("12 Jobs Completed", fontSize = 14.sp, color = OnSurfaceVariant)
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(16.dp))
                            .background(SurfaceContainerLowest)
                            .border(1.dp, OutlineVariant.copy(alpha=0.4f), RoundedCornerShape(16.dp))
                            .padding(16.dp)
                    ) {
                        Text("Today", fontSize = 12.sp, color = OnSurfaceVariant)
                        Text("₹596", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = OnSurface)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("3 Jobs Completed", fontSize = 14.sp, color = OnSurfaceVariant)
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
            
            item {
                Text("RECENT TRANSACTIONS", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = OnSurfaceVariant, modifier = Modifier.padding(bottom = 16.dp))
            }
            
            items(4) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(SurfaceContainerLowest)
                        .border(1.dp, OutlineVariant.copy(alpha=0.2f), RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(48.dp).background(SurfaceContainer, CircleShape), contentAlignment = Alignment.Center) {
                            Icon(Icons.Filled.ReceiptLong, contentDescription = null, tint = OnSurfaceVariant)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("#CHD1234${5-index}", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = OnSurface)
                            Text(if (index < 2) "Today, ${10-index}:30 AM" else "Yesterday, ${6-index}:20 PM", fontSize = 14.sp, color = OnSurfaceVariant)
                        }
                    }
                    Text("+₹119", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Tertiary)
                }
            }
        }
    }
}
