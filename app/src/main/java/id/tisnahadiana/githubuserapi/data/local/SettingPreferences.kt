package id.tisnahadiana.githubuserapi.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.prefDataStore by preferencesDataStore("settings")

class SettingPreferences constructor(context: Context) {

    private val settingsDataStore = context.prefDataStore
    private val themeKEY = booleanPreferencesKey(THEME_SETTINGS)

    fun getThemeSetting(): Flow<Boolean> =
        settingsDataStore.data.map { preferences ->
            preferences[themeKEY] ?: false
        }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        settingsDataStore.edit { preferences ->
            preferences[themeKEY] = isDarkModeActive
        }
    }

    companion object {
        private const val THEME_SETTINGS = "theme_settings"
    }
}