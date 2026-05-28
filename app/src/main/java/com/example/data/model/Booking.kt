package com.example.data.model

import com.google.firebase.firestore.FieldValue

data class Booking(
    val bookingId: String = "",
    val hirerId: String = "",
    val workerId: String? = null,
    val pickupLat: Double = 0.0,
    val pickupLon: Double = 0.0,
    val status: String = "PENDING", // PENDING, ACCEPTED, ONGOING, COMPLETED
    val price: Double = 0.0,
    val createdAt: Any = FieldValue.serverTimestamp()
) {
    fun toMap(): Map<String, Any?> = mapOf(
        "bookingId" to bookingId,
        "hirerId" to hirerId,
        "workerId" to workerId,
        "pickupLat" to pickupLat,
        "pickupLon" to pickupLon,
        "status" to status,
        "price" to price,
        "createdAt" to createdAt
    )
}
