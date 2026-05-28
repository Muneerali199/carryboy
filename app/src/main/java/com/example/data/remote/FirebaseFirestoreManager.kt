package com.example.data.remote

import com.example.data.model.Booking
import com.example.data.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseFirestoreManager {
    private val db = FirebaseFirestore.getInstance()

    suspend fun saveUser(user: User): Result<Unit> = runCatching {
        db.collection("users").document(user.uid).set(user.toMap()).await()
    }

    suspend fun getUser(uid: String): Result<User?> = runCatching {
        val snapshot = db.collection("users").document(uid).get().await()
        if (snapshot.exists() && snapshot.data != null) {
            User.fromMap(snapshot.data!!)
        } else null
    }

    suspend fun updateUser(user: User): Result<Unit> = runCatching {
        db.collection("users").document(user.uid).update(user.toMap()).await()
    }

    // Client-side alternative to Cloud Functions:
    suspend fun createBooking(booking: Booking): Result<String> = runCatching {
        val docRef = if (booking.bookingId.isEmpty()) {
            db.collection("bookings").document() // auto-generate ID
        } else {
            db.collection("bookings").document(booking.bookingId)
        }
        
        val bookingToSave = booking.copy(bookingId = docRef.id)
        docRef.set(bookingToSave.toMap()).await()
        docRef.id
    }
}
