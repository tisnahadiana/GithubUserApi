package id.tisnahadiana.githubuserapi.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.tisnahadiana.githubuserapi.core.data.source.local.room.FavoriteUserDao
import id.tisnahadiana.githubuserapi.core.data.source.local.room.UserDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase = Room.databaseBuilder(
        context,
        UserDatabase::class.java, "user_database"
    ).fallbackToDestructiveMigration().build()
    @Provides
    fun provideFavoriteUserDao(database: UserDatabase): FavoriteUserDao = database.favoriteUserDao()

}