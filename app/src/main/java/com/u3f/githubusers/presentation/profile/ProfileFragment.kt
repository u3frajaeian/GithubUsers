package com.u3f.githubusers.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.u3f.githubusers.R
import com.u3f.githubusers.base.delegate.viewBinding
import com.u3f.githubusers.base.extension.toast
import com.u3f.githubusers.databinding.FragmentProfileBinding
import com.u3f.githubusers.domain.model.profile.ProfileModel
import dagger.hilt.android.AndroidEntryPoint
import com.u3f.githubusers.presentation.extension.load
import com.u3f.githubusers.presentation.extension.observe
import com.u3f.githubusers.presentation.users.followers.FollowersFragment
import com.u3f.githubusers.presentation.users.following.FollowingFragment

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding()
    private val viewModel: ProfileViewModel by viewModels()
    private val pagerAdapter: PagerAdapter by lazy { PagerAdapter(this) }
    private lateinit var user: ProfileModel
    val args: ProfileFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.state, stateObserver)

        initView()
        viewModel.getUser(args.userData.username)


    }

    private fun initView() {
        activity?.title = args.userData.username
        binding.imgAvatar.load(args.userData.avatarUrl)
//        binding.fabFav.setOnClickListener { viewModel.addToFavourite(user) }
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {

                0 -> tab.text = getString(R.string.follower)
                1 -> tab.text = getString(R.string.following)
            }
        }.attach()


    }

    private val stateObserver = Observer<ProfileState> { state ->
        when (state) {
            is ProfileState.UserFetched -> {
                user = state.user
                binding.imgAvatar.load(user.avatarUrl)
                binding.tvFullName.text = user.name
                binding.tvUsername.text = user.login
                binding.followerCount.text = user.followers.toString()
                binding.followingCount.text = user.following.toString()
                binding.repositoryCount.text = user.publicRepos.toString()
            }

            is ProfileState.Error -> {
                toast(state.error.message)
            }

            else -> Unit
        }
    }

    private inner class PagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> FollowersFragment(args.userData.username)
            1 -> FollowingFragment(args.userData.username)
            else -> FollowingFragment(args.userData.username)
        }

    }


}