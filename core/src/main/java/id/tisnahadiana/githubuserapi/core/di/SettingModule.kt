package id.tisnahadiana.githubuserapi.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.tisnahadiana.githubuserapi.core.source.local.SettingPreferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SettingModule {

    @Provides
    @Singleton
    fun provideSettingPreferences(@ApplicationContext context: Context): SettingPreferences {
        return SettingPreferences(context)
    }

}