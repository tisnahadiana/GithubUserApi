package id.tisnahadiana.githubuserapi.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.tisnahadiana.githubuserapi.core.source.local.room.FavoriteUserDao
import id.tisnahadiana.githubuserapi.core.source.local.room.UserDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("tisnahadiana".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java, "user_database"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideFavoriteUserDao(database: UserDatabase): FavoriteUserDao = database.favoriteUserDao()

}