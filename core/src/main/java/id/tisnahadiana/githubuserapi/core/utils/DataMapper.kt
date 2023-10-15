package id.tisnahadiana.githubuserapi.core.utils

import id.tisnahadiana.githubuserapi.core.api.SearchResponse
import id.tisnahadiana.githubuserapi.core.api.UserResponse
import id.tisnahadiana.githubuserapi.core.domain.model.Favorite
import id.tisnahadiana.githubuserapi.core.domain.model.GithubUserDetail
import id.tisnahadiana.githubuserapi.core.domain.model.Search
import id.tisnahadiana.githubuserapi.core.domain.model.User
import id.tisnahadiana.githubuserapi.core.source.local.entity.FavoriteEntity
import id.tisnahadiana.githubuserapi.core.source.remote.response.GithubDetailResponse

object DataMapper {
    fun mapUserResponseToDomain(userResponse: UserResponse): User {
        return User(
            userResponse.gistsUrl,
            userResponse.reposUrl,
            userResponse.followingUrl,
            userResponse.starredUrl,
            userResponse.login,
            userResponse.followersUrl,
            userResponse.type,
            userResponse.url,
            userResponse.subscriptionsUrl,
            userResponse.score,
            userResponse.receivedEventsUrl,
            userResponse.avatarUrl,
            userResponse.eventsUrl,
            userResponse.htmlUrl,
            userResponse.siteAdmin,
            userResponse.id,
            userResponse.gravatarId,
            userResponse.nodeId,
            userResponse.organizationsUrl
        )
    }

    fun mapSearchResponseToDomain(response: SearchResponse): Search {
        return Search(
            totalCount = response.totalCount,
            incompleteResults = response.incompleteResults,
            users = response.items?.map { mapUserResponseToDomain(it) }
        )
    }

    fun mapGithubDetailResponseToUserDetail(response: GithubDetailResponse): GithubUserDetail {
        return GithubUserDetail(
            gistsUrl = response.gistsUrl.orEmpty(),
            reposUrl = response.reposUrl.orEmpty(),
            followingUrl = response.followingUrl.orEmpty(),
            twitterUsername = response.twitterUsername?.toString().orEmpty(),
            bio = response.bio?.toString().orEmpty(),
            createdAt = response.createdAt.orEmpty(),
            login = response.login.orEmpty(),
            type = response.type.orEmpty(),
            blog = response.blog.orEmpty(),
            subscriptionsUrl = response.subscriptionsUrl.orEmpty(),
            updatedAt = response.updatedAt.orEmpty(),
            siteAdmin = response.siteAdmin ?: false,
            company = response.company.orEmpty(),
            id = response.id ?: 0,
            publicRepos = response.publicRepos ?: 0,
            gravatarId = response.gravatarId.orEmpty(),
            email = response.email?.toString().orEmpty(),
            organizationsUrl = response.organizationsUrl.orEmpty(),
            hireable = response.hireable?.toString().orEmpty(),
            starredUrl = response.starredUrl.orEmpty(),
            followersUrl = response.followersUrl.orEmpty(),
            publicGists = response.publicGists ?: 0,
            url = response.url.orEmpty(),
            receivedEventsUrl = response.receivedEventsUrl.orEmpty(),
            followers = response.followers ?: 0,
            avatarUrl = response.avatarUrl.orEmpty(),
            eventsUrl = response.eventsUrl.orEmpty(),
            htmlUrl = response.htmlUrl.orEmpty(),
            following = response.following ?: 0,
            name = response.name.orEmpty(),
            location = response.location.orEmpty(),
            nodeId = response.nodeId.orEmpty()
        )
    }
    fun mapFavoriteEntityToDomain(entity: FavoriteEntity): Favorite {
        return Favorite(
            login = entity.login,
            id = entity.id,
            avatarUrl = entity.avatar_url,
            htmlUrl = entity.html_url
        )
    }

    fun mapFavoriteToEntity(favorite: Favorite): FavoriteEntity {
        return FavoriteEntity(
            login = favorite.login,
            id = favorite.id,
            avatar_url = favorite.avatarUrl,
            html_url = favorite.htmlUrl
        )
    }
}