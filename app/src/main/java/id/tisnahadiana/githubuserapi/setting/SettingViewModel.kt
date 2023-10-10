package id.tisnahadiana.githubuserapi.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.tisnahadiana.githubuserapi.core.data.source.local.SettingPreferences
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SettingViewModel @Inject constructor(private val pref: SettingPreferences) : ViewModel() {

    fun getTheme() = pref.getThemeSetting().asLiveData()

    fun saveTheme(isDark: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDark)
        }
    }
}