package id.tisnahadiana.githubuserapi.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import id.tisnahadiana.githubuserapi.R
import id.tisnahadiana.githubuserapi.databinding.ActivityDetailUserBinding
import id.tisnahadiana.githubuserapi.data.model.DetailViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatarUrl = intent.getStringExtra(EXTRA_URL)
        val htmlUrl = intent.getStringExtra(EXTRA_HTML)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        if (username != null) {
            viewModel.setUserDetail(username)
        }

        viewModel.getUserDetail().observe(this) {
            if (it != null) {
                binding.apply {
                    txtName.text = it.name
                    txtUsername.text = it.login
                    txtFollowers.text = resources.getString(R.string.followers, it.followers)
                    txtFollowing.text = resources.getString(R.string.following, it.following)
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatarUrl)
                        .placeholder(R.drawable.baseline_account_circle_24)
                        .into(imgProfile)
                }
            }
        }

        var _isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkUser(id)
            withContext(Dispatchers.Main) {
                if (count != null) {
                    if (count > 0) {
                        binding.toggleFavorite.isChecked = true
                        _isChecked = true
                    } else {
                        binding.toggleFavorite.isChecked = false
                        _isChecked = false
                    }
                }
            }
        }

        binding.toggleFavorite.setOnClickListener {
            _isChecked = !_isChecked
            if (_isChecked) {
                if (username != null) {
                    if (avatarUrl != null) {
                        if (htmlUrl != null) {
                            viewModel.addToFavorite(username, id, avatarUrl, htmlUrl)
                        }
                    }
                }
            } else {
                viewModel.removeFromFavorite(id)
            }
            binding.toggleFavorite.isChecked = _isChecked
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, intent.extras)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
                tab.text = resources.getString(SectionPagerAdapter.TAB_TITLES[position])
            }.attach()
        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_URL = "extra_url"
        const val EXTRA_HTML = "extra_html"
    }

}