package id.tisnahadiana.githubuserapi.core.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.tisnahadiana.githubuserapi.core.source.local.entity.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1
    , exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun favoriteUserDao(): FavoriteUserDao
}