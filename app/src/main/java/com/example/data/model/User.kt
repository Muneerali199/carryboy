package com.example.data.model

enum class UserRole {
    HIRER, WORKER
}

data class User(
    val uid: String = "",
    val email: String = "",
    val displayName: String = "",
    val phone: String = "",
    val role: UserRole = UserRole.WORKER,
    val profileImageUrl: String = "",
    val address: String = "",
    val bio: String = "",
    val isProfileComplete: Boolean = false,
    val isAadharVerified: Boolean = false,
    val aadharNumber: String = ""
) {
    fun toMap(): Map<String, Any> = mapOf(
        "uid" to uid,
        "email" to email,
        "displayName" to displayName,
        "phone" to phone,
        "role" to role.name,
        "profileImageUrl" to profileImageUrl,
        "address" to address,
        "bio" to bio,
        "isProfileComplete" to isProfileComplete,
        "isAadharVerified" to isAadharVerified,
        "aadharNumber" to aadharNumber
    )

    companion object {
        fun fromMap(map: Map<String, Any>): User = User(
            uid = map["uid"] as? String ?: "",
            email = map["email"] as? String ?: "",
            displayName = map["displayName"] as? String ?: "",
            phone = map["phone"] as? String ?: "",
            role = try {
                UserRole.valueOf(map["role"] as? String ?: "WORKER")
            } catch (_: Exception) {
                UserRole.WORKER
            },
            profileImageUrl = map["profileImageUrl"] as? String ?: "",
            address = map["address"] as? String ?: "",
            bio = map["bio"] as? String ?: "",
            isProfileComplete = map["isProfileComplete"] as? Boolean ?: false,
            isAadharVerified = map["isAadharVerified"] as? Boolean ?: false,
            aadharNumber = map["aadharNumber"] as? String ?: ""
        )
    }
}
