package id.tisnahadiana.githubuserapi.core.source.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class SettingPreferences @Inject constructor(@ApplicationContext private val context: Context) {

    private val Context.prefDataStore by preferencesDataStore("settings")

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