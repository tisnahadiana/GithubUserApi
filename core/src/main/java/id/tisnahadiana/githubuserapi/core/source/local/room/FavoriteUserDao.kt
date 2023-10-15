package id.tisnahadiana.githubuserapi.core.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.tisnahadiana.githubuserapi.core.source.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite_user")
    fun getFavoriteUser(): Flow<List<FavoriteEntity>>

    @Query("SELECT count(*) FROM favorite_user WHERE favorite_user.id = :id")
    fun checkUser(id: Int): Int

    @Query("DELETE FROM favorite_user WHERE favorite_user.id = :id")
    fun removeFromFavorite(id: Int): Int
}