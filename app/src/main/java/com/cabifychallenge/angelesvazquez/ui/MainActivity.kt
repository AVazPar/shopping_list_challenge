package com.cabifychallenge.angelesvazquez.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.cabifychallenge.angelesvazquez.R
import com.cabifychallenge.angelesvazquez.common.base.BaseActivity
import com.cabifychallenge.angelesvazquez.common.uicomponents.BottomNavigationManager
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Author: ÁngelesVP
 *
 * This is the main activity, I've used a Activity class where I add the viewModel into it.
 * We can use this viewModel in all child fragments like a sharedviewmodel.
 *
 * Here we configure the toolbar of the application and the bottom navigation manager.
 */
class MainActivity : BaseActivity<MainActivityViewModel>(MainActivityViewModel::class) {

    override fun layoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null)
            navigationManager.init()
        navigationManager.initListeners()

        viewModel.getScreenTitle().observe(this, Observer {
            iv_back?.visibility = if(it.backIcon) View.VISIBLE else View.GONE
            iv_back?.setOnClickListener {
                onBackPressed()
            }
            iv_info?.setOnClickListener {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse("https://github.com/cabify/MobileChallenge")
                startActivity(openURL)
            }
        })
    }

    /**
     * We use lazy object because it's a variable created in the same moment that we use it.
     * You will see more details in Bottom Navigation Manager class.
     */
    private val navigationManager: BottomNavigationManager by lazy {
        BottomNavigationManager(
            bottomIdGraphId = mapOf(
                R.id.navigation_home to R.navigation.home_navigation,
                R.id.navigation_shopping to R.navigation.shopping_navigation
            ),
            bottomNavigation = nav_view,
            mainBottomId = R.id.navigation_home,
            containerId = R.id.nav_host_fragment,
            fragmentManager = supportFragmentManager
        )
    }

    /**
     * We have to override these methods for our custom bottom navigation manager.
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        navigationManager.restore()
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onBackPressed() {
        if(!navigationManager.backPressed())
            super.onBackPressed()
    }
}
