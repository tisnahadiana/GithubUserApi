package id.tisnahadiana.githubuserapi.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.tisnahadiana.githubuserapi.R
import id.tisnahadiana.githubuserapi.databinding.FragmentFollowBinding
import id.tisnahadiana.githubuserapi.main.GithubUserAdapter

@AndroidEntryPoint
class FollowersFragment : Fragment(R.layout.fragment_follow) {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: GithubUserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arg = arguments
        username = arg?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentFollowBinding.bind(view)

        adapter = GithubUserAdapter()

        binding?.rvUser?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@FollowersFragment.adapter
        }

        viewModel = ViewModelProvider(this).get(FollowersViewModel::class.java)
        viewModel.getListFollowers().observe(viewLifecycleOwner) { users ->
            adapter.setList(users)
            showLoading(false)
        }

        showLoading(true)
        viewModel.setListFollowers(username)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(state: Boolean) {
        binding?.progressBar?.visibility = if (state) View.VISIBLE else View.GONE
    }
}