package id.tisnahadiana.githubuserapi.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.tisnahadiana.githubuserapi.core.data.source.local.entity.FavoriteUser

@Database(
    entities = [FavoriteUser::class],
    version = 1
    , exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun favoriteUserDao(): FavoriteUserDao
}