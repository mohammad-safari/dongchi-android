package ce.bhesab.dongchi.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class UserSettingsRepository(private val context: Context) {
    private companion object {
        private val userPreferenceKey = stringPreferencesKey("user")
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    }

    suspend fun getToken() {
        Result.runCatching {
            context.dataStore.data.map { preferences ->
                preferences[userPreferenceKey] ?: ""
            }.firstOrNull()
        }
    }

    suspend fun saveToken(token: String) {
        Result.runCatching {
            context.dataStore.edit { preferences ->
                preferences[userPreferenceKey] = token
            }
        }
    }

}