package com.u3f.githubusers.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import com.u3f.githubusers.R
import com.u3f.githubusers.base.delegate.viewBinding
import com.u3f.githubusers.base.extension.navigateSafe
import com.u3f.githubusers.databinding.ActivityMainBinding
import com.u3f.githubusers.presentation.extension.hide
import com.u3f.githubusers.presentation.extension.show
import dagger.hilt.android.AndroidEntryPoint
import com.u3f.githubusers.presentation.navigation.NavManager
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()
    private val navController get() = Navigation.findNavController(this, R.id.nav_host_fragment)

    @Inject
    lateinit var navManager: NavManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initNavManager()


    }



    private fun initNavManager() {

        navManager.setOnNavEvent {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)

            currentFragment?.navigateSafe(it)
        }

    }


}

