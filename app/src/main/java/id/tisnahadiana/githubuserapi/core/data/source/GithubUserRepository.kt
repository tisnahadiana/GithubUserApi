package id.tisnahadiana.githubuserapi.core.data.source

import androidx.lifecycle.LiveData
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.local.LocalDataSource
import id.tisnahadiana.githubuserapi.core.data.source.local.entity.FavoriteUser
import id.tisnahadiana.githubuserapi.core.data.source.remote.RemoteDataSource
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse
import id.tisnahadiana.githubuserapi.core.domain.repository.IGithubUserRepository

class GithubUserRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IGithubUserRepository {

    companion object {
        @Volatile
        private var instance: GithubUserRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource
        ): GithubUserRepository =
            instance ?: synchronized(this) {
                instance ?: GithubUserRepository(remoteData, localData)
            }
    }

    override fun getFollowers(username: String): LiveData<List<User>?> {
        return remoteDataSource.getFollowers(username)
    }

    override fun getFollowing(username: String): LiveData<List<User>?> {
        return remoteDataSource.getFollowing(username)
    }

    override fun getUserDetail( username: String ): LiveData<GithubDetailResponse?> {
        return remoteDataSource.getUserDetail(username)
    }

    override fun getSearchUsers(query: String): LiveData<List<User>?> {
        return remoteDataSource.getSearchUsers(query)
    }

    override fun getFavoriteUser(): LiveData<List<FavoriteUser>> {
        return localDataSource.getFavoriteUser()
    }

    override fun addToFavorite(username: String, id: Int, avatarUrl: String, htmlUrl: String) {
        return localDataSource.addToFavorite(username, id, avatarUrl, htmlUrl)
    }

    override fun checkUser(id: Int) : Int {
        return localDataSource.checkUser(id)
    }

    override fun removeFromFavorite(id: Int) {
        return localDataSource.removeFromFavorite(id)
    }
}