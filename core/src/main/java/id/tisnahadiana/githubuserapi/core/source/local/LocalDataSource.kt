package id.tisnahadiana.githubuserapi.core.source.local

import id.tisnahadiana.githubuserapi.core.source.local.entity.FavoriteUser
import id.tisnahadiana.githubuserapi.core.source.local.room.FavoriteUserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val favoriteUserDao: FavoriteUserDao) {

    fun getFavoriteUser(): Flow<List<FavoriteUser>> {
        return favoriteUserDao.getFavoriteUser()
    }

    fun addToFavorite(username: String, id: Int, avatarUrl: String, htmlUrl: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = FavoriteUser(
                username,
                id,
                avatarUrl,
                htmlUrl
            )
            favoriteUserDao.addToFavorite(user)
        }
    }

    fun checkUser(id: Int) = favoriteUserDao.checkUser(id)

    fun removeFromFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            favoriteUserDao.removeFromFavorite(id)
        }
    }
}