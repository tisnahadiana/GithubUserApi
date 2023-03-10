package id.tisnahadiana.githubuserapi.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.tisnahadiana.githubuserapi.databinding.ActivityDetailUserBinding
import id.tisnahadiana.githubuserapi.model.DetailViewModel

class DetailUserActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        username?.let {
            viewModel.setUserDetail(it)
        }

        viewModel.getUserDetail().observe(this) {
            if (it != null) {
                binding.apply {
                    txtName.text = it.name
                    txtUsername.text = it.login
                    txtFollowers.text = " ${it.followers} Followers"
                    txtFollowing.text = " ${it.following} Following"
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatarUrl)
                        .into(imgProfile)
                }
            }
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}