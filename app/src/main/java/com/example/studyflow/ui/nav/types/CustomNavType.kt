package com.example.studyflow.ui.nav.types

import android.net.Uri
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import com.example.studyflow.model.User
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val UserType = object : NavType<User>(
        isNullableAllowed = false
    ) {
        override fun put(bundle: SavedState, key: String, value: User) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun get(bundle: SavedState, key: String): User? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): User {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: User): String {
            return Uri.encode(Json.encodeToString(value))
        }
    }
}