package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.data.local.PreferencesManager
import com.example.data.model.UserRole
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.Surface as AppSurface
import com.example.ui.viewmodel.AuthViewModel
import com.example.ui.viewmodel.ProfileViewModel
import org.osmdroid.config.Configuration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().userAgentValue = packageName
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
    val authViewModel: AuthViewModel = viewModel()
    val authState by authViewModel.authState.collectAsState()
    var selectedRole by remember { mutableStateOf<UserRole?>(null) }
    val preferencesManager = remember { PreferencesManager(navController.context.applicationContext) }

    Surface(modifier = Modifier.fillMaxSize(), color = AppSurface) {
        NavHost(
            navController = navController,
            startDestination = "splash"
        ) {
            composable("splash") {
                SplashScreen(
                    onSplashComplete = {
                        val dest = when {
                            !preferencesManager.isOnboardingComplete -> "onboarding"
                            authState.isLoggedIn -> "main"
                            else -> "role_selection"
                        }
                        navController.navigate(dest) {
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                )
            }

            composable("onboarding") {
                OnboardingScreen(
                    onComplete = {
                        preferencesManager.isOnboardingComplete = true
                        navController.navigate("role_selection") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    }
                )
            }

            composable("role_selection") {
                RoleSelectionScreen(
                    onRoleSelected = { role ->
                        selectedRole = role
                        navController.navigate("signup/$role") {
                            popUpTo("role_selection") { inclusive = true }
                        }
                    },
                    onNavigateToLogin = {
                        navController.navigate("login") {
                            popUpTo("role_selection") { inclusive = true }
                        }
                    }
                )
            }

            composable("login") {
                LoginScreen(
                    authState = authState,
                    onLogin = { email, password -> authViewModel.login(email, password) },
                    onGoogleSignIn = { authViewModel.getGoogleSignInIntent() },
                    onGoogleSignInResult = { idToken -> authViewModel.signInWithGoogle(idToken, null) },
                    onNavigateToSignUp = {
                        navController.popBackStack()
                    },
                    onClearError = { authViewModel.clearError() }
                )
            }

            composable("signup/{role}") { backStackEntry ->
                val roleStr = backStackEntry.arguments?.getString("role") ?: "WORKER"
                val role = try { UserRole.valueOf(roleStr) } catch (_: Exception) { UserRole.WORKER }
                SignUpScreen(
                    authState = authState,
                    role = role,
                    onSignUp = { email, password -> authViewModel.signUp(email, password, role) },
                    onGoogleSignIn = { authViewModel.getGoogleSignInIntent() },
                    onGoogleSignInResult = { idToken, selectedRole -> authViewModel.signInWithGoogle(idToken, selectedRole) },
                    onNavigateToLogin = {
                        navController.navigate("login") {
                            popUpTo("signup/$role") { inclusive = true }
                        }
                    },
                    onClearError = { authViewModel.clearError() }
                )
            }

            composable("profile") {
                val profileViewModel: ProfileViewModel = viewModel()
                val profileState by profileViewModel.profileState.collectAsState()

                LaunchedEffect(authState.user) {
                    authState.user?.uid?.let { profileViewModel.loadProfile(it) }
                }

                val displayUser = profileState.user ?: authState.user

                if (displayUser != null) {
                    ProfileScreen(
                        user = displayUser,
                        isEditing = !displayUser.isProfileComplete,
                        isSaving = profileState.isSaving,
                        error = profileState.error,
                        isSaved = profileState.isSaved,
                        onSave = { user -> profileViewModel.saveProfile(user) },
                        onUpdate = { user -> profileViewModel.updateProfile(user) },
                        onBack = { navController.popBackStack() },
                        onLogout = {
                            authViewModel.logout()
                            preferencesManager.isOnboardingComplete = false
                            navController.navigate("splash") {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    )
                }
            }

            composable("main") {
                val user = authState.user
                if (user != null) {
                    if (!user.isProfileComplete) {
                        LaunchedEffect(Unit) {
                            navController.navigate("profile") {
                                popUpTo("main") { inclusive = true }
                            }
                        }
                    } else if (user.role == UserRole.WORKER && !user.isAadharVerified) {
                        LaunchedEffect(Unit) {
                            navController.navigate("aadhar") {
                                popUpTo("main") { inclusive = true }
                            }
                        }
                    } else {
                        if (user.role == UserRole.HIRER) {
                            HirerDashboardScreen(
                                user = user,
                                onNavigateToProfile = { navController.navigate("profile") },
                                onNavigateToNewBooking = { navController.navigate("new_request") }, 
                                onNavigateToMyBookings = { },
                                onLogout = {
                                    authViewModel.logout()
                                    navController.navigate("splash") {
                                        popUpTo(0) { inclusive = true }
                                    }
                                }
                            )
                        } else {
                            HelperDashboardScreen(
                                onNavigateToEarnings = { navController.navigate("earnings") },
                                onNavigateToNewRequest = { navController.navigate("new_request") },
                                onNavigateToOngoing = { navController.navigate("ongoing") },
                                onNavigateToProfile = { navController.navigate("profile") }
                            )
                        }
                    }
                }
            }

            composable("aadhar") {
                val profileViewModel: ProfileViewModel = viewModel()
                val user = authState.user
                if (user != null) {
                    AadharVerificationScreen(
                        user = user,
                        onVerify = { updatedUser ->
                            profileViewModel.saveProfile(updatedUser)
                            navController.navigate("main") {
                                popUpTo("aadhar") { inclusive = true }
                            }
                        },
                        onLogout = {
                            authViewModel.logout()
                            navController.navigate("splash") {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    )
                }
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
                    onStart = { navController.navigate("ongoing") }
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
                        navController.navigate("main") {
                            popUpTo("main") { inclusive = true }
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

    LaunchedEffect(authState.isLoggedIn) {
        if (authState.isLoggedIn && authState.user != null) {
            val currentDest = navController.currentDestination?.route
            if (currentDest == "login" || currentDest?.startsWith("signup") == true || currentDest == "role_selection") {
                if (!authState.user!!.isProfileComplete) {
                    navController.navigate("profile") {
                        popUpTo(0) { inclusive = true }
                    }
                } else if (authState.user!!.role == UserRole.WORKER && !authState.user!!.isAadharVerified) {
                    navController.navigate("aadhar") {
                        popUpTo(0) { inclusive = true }
                    }
                } else {
                    navController.navigate("main") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }
        }
    }
}
