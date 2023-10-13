package id.tisnahadiana.githubuserapi.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.EntryPointAccessors
import id.tisnahadiana.githubuserapi.R
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.source.local.entity.FavoriteUser
import id.tisnahadiana.githubuserapi.detail.DetailUserActivity
import id.tisnahadiana.githubuserapi.di.FavoriteModuleDependencies
import id.tisnahadiana.githubuserapi.favorite.databinding.ActivityFavoriteBinding
import id.tisnahadiana.githubuserapi.main.GithubUserAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: GithubUserAdapter
    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.favorite_page)

        adapter = GithubUserAdapter()
        adapter.setOnItemClickCallback(object : GithubUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                Intent(this@FavoriteActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_URL, data.avatarUrl)
                    it.putExtra(DetailUserActivity.EXTRA_HTML, data.htmlUrl)
                    startActivity(it)
                }
            }

        })

        binding.apply {
            rvUserFav.setHasFixedSize(true)
            rvUserFav.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvUserFav.adapter = adapter
        }

        lifecycleScope.launch {
            viewModel.getFavoriteUser().observe(this@FavoriteActivity) { users ->
                if (users != null) {
                    val list = mapList(users)
                    adapter.setList(list)
                }
            }
        }
    }

    private fun mapList(users: List<FavoriteUser>): ArrayList<User> {
        val listUsers = ArrayList<User>()
        for (user in users) {
            val userMapped = User(
                login = user.login,
                id = user.id,
                avatarUrl = user.avatar_url,
                htmlUrl = user.html_url
            )
            listUsers.add(userMapped)
        }
        return listUsers
    }
}