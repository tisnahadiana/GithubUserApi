package id.tisnahadiana.githubuserapi.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.tisnahadiana.githubuserapi.R
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.local.SettingPreferences
import id.tisnahadiana.githubuserapi.databinding.ActivityMainBinding
import id.tisnahadiana.githubuserapi.detail.DetailUserActivity
import id.tisnahadiana.githubuserapi.favorite.FavoriteActivity
import id.tisnahadiana.githubuserapi.setting.SettingActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GithubUserAdapter
    private lateinit var viewModel: MainViewModel
    private val viewModelsetting by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelsetting.getTheme().observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        adapter = GithubUserAdapter()

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = adapter

        adapter.setOnItemClickCallback(object : GithubUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_URL, data.avatarUrl)
                    it.putExtra(DetailUserActivity.EXTRA_HTML, data.htmlUrl)
                    startActivity(it)
                }
            }

        })

        viewModel.getSearchUsers().observe(this) { users ->
            users?.let {
                adapter.setList(it)
                showLoading(false)
            }
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                AlertDialog.Builder(this@MainActivity)
                    .setMessage(R.string.exit_hint)
                    .setPositiveButton("Ya") { _, _ ->
                        finish()
                    }
                    .setNegativeButton("Tidak") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as? SearchView
        searchView?.queryHint = resources.getString(R.string.search_hint)

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchUser(it)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    viewModel.setSearchUsers("")
                } else {
                    viewModel.setSearchUsers(newText)
                }
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite_menu -> {
                Intent(this, FavoriteActivity::class.java).also {
                    startActivity(it)
                }
            }

            R.id.setting -> {
                Intent(this, SettingActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun searchUser(query: String) {
        if (query.isEmpty()) return
        showLoading(true)
        viewModel.setSearchUsers(query)
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

}