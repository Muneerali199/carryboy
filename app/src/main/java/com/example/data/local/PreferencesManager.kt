package com.example.data.local

import android.content.Context

class PreferencesManager(context: Context) {
    private val prefs = context.getSharedPreferences("carryboy_prefs", Context.MODE_PRIVATE)

    var isOnboardingComplete: Boolean
        get() = prefs.getBoolean("onboarding_complete", false)
        set(value) = prefs.edit().putBoolean("onboarding_complete", value).apply()
}
