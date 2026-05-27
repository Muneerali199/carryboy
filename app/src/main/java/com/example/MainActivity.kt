package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.Surface as AppSurface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme(dynamicColor = false) {
                LaborConnectApp()
            }
        }
    }
}

@Composable
fun LaborConnectApp() {
    val navController = rememberNavController()
    Surface(modifier = Modifier.fillMaxSize(), color = AppSurface) {
        NavHost(navController = navController, startDestination = "dashboard") {
            composable("dashboard") {
                HelperDashboardScreen(
                    onNavigateToEarnings = { navController.navigate("earnings") },
                    onNavigateToNewRequest = { navController.navigate("new_request") },
                    onNavigateToOngoing = { navController.navigate("ongoing") }
                )
            }
            composable("new_request") {
                NewBookingRequestScreen(
                    onBack = { navController.popBackStack() },
                    onAccept = { navController.navigate("navigate_to_pickup") }
                )
            }
            composable("navigate_to_pickup") {
                NavigateToPickupScreen(
                    onBack = { navController.popBackStack() },
                    onReached = { navController.navigate("otp") }
                )
            }
            composable("otp") {
                StartWorkOtpScreen(
                    onBack = { navController.popBackStack() },
                    onStart = { navController.navigate("ongoing") } // Normally it would start work, let's go to ongoing for now
                )
            }
            composable("ongoing") {
                OngoingBookingDetailsScreen(
                    onBack = { navController.popBackStack() },
                    onStartWork = { navController.navigate("completed") }
                )
            }
            composable("completed") {
                BookingCompletedScreen(
                    onBackToHome = {
                        navController.navigate("dashboard") {
                            popUpTo("dashboard") { inclusive = false }
                        }
                    }
                )
            }
            composable("earnings") {
                EarningsOverviewScreen(
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

