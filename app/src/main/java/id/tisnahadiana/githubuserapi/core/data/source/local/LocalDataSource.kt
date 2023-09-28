package id.tisnahadiana.githubuserapi.core.data.source.local

import androidx.lifecycle.LiveData
import id.tisnahadiana.githubuserapi.core.data.source.local.room.FavoriteUserDao

class LocalDataSource private constructor(private val favoriteUserDao: FavoriteUserDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(favoriteUserDao: FavoriteUserDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(favoriteUserDao)
            }
    }


}