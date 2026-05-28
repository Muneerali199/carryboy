package com.example.data.repository

import com.example.data.model.User
import com.example.data.model.UserRole
import com.example.data.remote.FirebaseAuthManager
import com.example.data.remote.FirebaseFirestoreManager
import com.google.firebase.auth.FirebaseUser

class UserRepository(
    private val authManager: FirebaseAuthManager = FirebaseAuthManager(),
    private val firestoreManager: FirebaseFirestoreManager = FirebaseFirestoreManager()
) {
    val isLoggedIn: Boolean get() = authManager.isLoggedIn
    val currentFirebaseUser: FirebaseUser? get() = authManager.currentUser

    suspend fun signUp(email: String, password: String, role: UserRole): Result<User> {
        val authResult = authManager.signUp(email, password)
        return authResult.mapCatching { firebaseUser ->
            val user = User(
                uid = firebaseUser.uid,
                email = email,
                displayName = firebaseUser.displayName ?: "",
                phone = firebaseUser.phoneNumber ?: "",
                profileImageUrl = firebaseUser.photoUrl?.toString() ?: "",
                role = role
            )
            firestoreManager.saveUser(user).getOrThrow()
            user
        }
    }

    suspend fun login(email: String, password: String): Result<User> {
        val authResult = authManager.login(email, password)
        return authResult.mapCatching { firebaseUser ->
            val user = firestoreManager.getUser(firebaseUser.uid).getOrThrow()
            user ?: User(uid = firebaseUser.uid, email = email)
        }
    }

    suspend fun signInWithGoogle(idToken: String, role: UserRole? = null): Result<User> {
        val authResult = authManager.signInWithGoogle(idToken)
        return authResult.mapCatching { firebaseUser ->
            val existingUser = firestoreManager.getUser(firebaseUser.uid).getOrNull()
            if (existingUser != null) {
                existingUser
            } else {
                val newUser = User(
                    uid = firebaseUser.uid,
                    email = firebaseUser.email ?: "",
                    displayName = firebaseUser.displayName ?: "",
                    phone = firebaseUser.phoneNumber ?: "",
                    profileImageUrl = firebaseUser.photoUrl?.toString() ?: "",
                    role = role ?: UserRole.WORKER
                )
                firestoreManager.saveUser(newUser).getOrThrow()
                newUser
            }
        }
    }

    suspend fun getUser(uid: String): Result<User?> = firestoreManager.getUser(uid)

    suspend fun updateUser(user: User): Result<Unit> = firestoreManager.updateUser(user)

    suspend fun saveUser(user: User): Result<Unit> = firestoreManager.saveUser(user)

    fun logout() = authManager.logout()
}
