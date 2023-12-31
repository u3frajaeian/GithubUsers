package com.u3f.githubusers.presentation.users.following

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.u3f.githubusers.R
import com.u3f.githubusers.base.delegate.viewBinding
import com.u3f.githubusers.base.extension.toast
import com.u3f.githubusers.databinding.FragmentFollowerBinding
import com.u3f.githubusers.presentation.extension.observe
import com.u3f.githubusers.presentation.extension.visible
import dagger.hilt.android.AndroidEntryPoint
import com.u3f.githubusers.presentation.navigation.NavManager
import com.u3f.githubusers.presentation.users.list.FollowersAdapter
import javax.inject.Inject

@AndroidEntryPoint
class FollowingFragment(val username: String) : Fragment(R.layout.fragment_follower) {

    private val binding: FragmentFollowerBinding by viewBinding()
    private val viewModel: FollowingViewModel by viewModels()

    private val followersAdapter: FollowersAdapter by lazy { FollowersAdapter() }

    @Inject
    lateinit var navManager: NavManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.state, stateObserver)
        initView()
        viewModel.getFollowing(username)


    }

    private val stateObserver = Observer<FollowingState> { state ->
        binding.shimmer.visible = state is FollowingState.Loading
        when (state) {
            is FollowingState.FollowingFetched -> {
                followersAdapter.users = state.users
                followersAdapter.notifyDataSetChanged()
            }

            is FollowingState.Error -> {
                toast(state.error.message)
            }

            else -> Unit
        }
    }

    private fun initView() {
        binding.rvRepos.apply {
            adapter = followersAdapter
        }
    }



}