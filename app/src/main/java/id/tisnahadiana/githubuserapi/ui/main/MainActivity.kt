package id.tisnahadiana.githubuserapi.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.tisnahadiana.githubuserapi.R
import id.tisnahadiana.githubuserapi.databinding.ActivityMainBinding
import id.tisnahadiana.githubuserapi.model.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GithubUserAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        adapter = GithubUserAdapter()
        adapter.notifyDataSetChanged()

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = adapter

//        adapter.setOnItemClickCallback(object : GithubUserAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: User) {
//                Intent(this@MainActivity, DetailUserActivity::class.java).also {
//                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
//                    startActivity(it)
//                }
//            }
//
//        })

        viewModel.getSearchUsers().observe(this) { users ->
            users?.let {
                adapter.setList(it)
                showLoading(false)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchUser(it)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
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