package com.cabifychallenge.angelesvazquez.common.uicomponents

import com.cabifychallenge.angelesvazquez.R
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * This class it's a custom bottom navigation manager. It allows to navigate as the way as we do
 * nowadays into this app.
 *
 * This method it's called from MainActivity to config it. It's important the id of the navigation graphs,
 * the first of all to show, the fragment manager, the id of the container and the id of the navigation.
 *
 * It's a fragment manager, so we need manage all the fragment that we are going to use, attach new ones,
 * detach the last ones...
 *
 * We have to know the life circle of this. For that reason we have to override some Main activity methods,
 * to call onRestoreInstanceState and onBackPress methods.
 *
 * This class is not mine, I get from another project, but I think it's useful to use it into several applications.
 *
 */
class BottomNavigationManager(
    private val bottomIdGraphId: Map<Int, Int>,
    private val mainBottomId: Int,
    private val containerId: Int,
    private val bottomNavigation: BottomNavigationView,
    private val fragmentManager: FragmentManager
) {
    private val graphIdTag: MutableMap<Int, String> = HashMap()

    fun init() {
        createGraphIdTag { tag, it ->
            val fragment = getOrCreateNavHostFragment(tag = tag, graphId = it.value)

            if (it.key == mainBottomId) {
                attachNavHostFragment(fragment = fragment)
            } else {
                detachNavHostFragment(fragment = fragment)
            }
        }

        bottomNavigation.selectedItemId = mainBottomId
    }

    private fun createGraphIdTag(actionInLoop: ((tag: String, item: Map.Entry<Int, Int>) -> Unit)? = null) {
        var i = 0
        bottomIdGraphId.forEach {
            val tag = "BottomNavigationManagerFragment${i++}"
            graphIdTag[it.value] = tag

            actionInLoop?.invoke(tag, it)
        }
    }

    private fun getOrCreateNavHostFragment(
        tag: String,
        graphId: Int
    ): Fragment {
        return fragmentManager.findFragmentByTag(tag) ?: run {
            val host = NavHostFragment.create(graphId)
            fragmentManager.beginTransaction()
                .add(containerId, host, tag)
                .commit()
            host
        }
    }

    private fun attachNavHostFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .attach(fragment)
            .setPrimaryNavigationFragment(fragment)
            .commit()
    }

    private fun detachNavHostFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .detach(fragment)
            .commit()
    }

    fun restore() {
        createGraphIdTag()
    }

    fun initListeners() {
        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            if (fragmentManager.isStateSaved || !bottomIdGraphId.containsKey(menuItem.itemId)) {
                false
            } else {
                itemSelected(menuItem = menuItem)

                true
            }
        }

        bottomNavigation.setOnNavigationItemReselectedListener {
            itemReselected()
        }
    }

    private fun itemSelected(menuItem: MenuItem) {
        getTag(bottomId = menuItem.itemId)?.let { tag ->
            fragmentManager.findFragmentByTag(tag)?.let { selectedFragment ->
                fragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.nav_default_enter_anim,
                        R.anim.nav_default_exit_anim,
                        R.anim.nav_default_pop_enter_anim,
                        R.anim.nav_default_pop_exit_anim
                    )
                    .attach(selectedFragment)
                    .setPrimaryNavigationFragment(selectedFragment)
                    .apply {
                        graphIdTag.forEach {
                            if (it.value != tag) {
                                fragmentManager.findFragmentByTag(it.value)
                                    ?.let { unselectedFragment ->
                                        detach(unselectedFragment)
                                    }
                            }
                        }
                    }
                    .commit()
            }
        }
    }

    private fun getTag(bottomId: Int): String? =
        getGraphId(bottomId = bottomId)?.let { graphId ->
            graphIdTag[graphId]
        }

    private fun getGraphId(bottomId: Int): Int? {
        return if (bottomIdGraphId.containsKey(mainBottomId)) {
            bottomIdGraphId.getValue(bottomId)
        } else {
            null
        }
    }

    private fun itemReselected() {
        getTag(bottomId = bottomNavigation.selectedItemId)?.let { tag ->
            (fragmentManager.findFragmentByTag(tag) as? NavHostFragment?)?.let { host ->
                host.navController.run {
                    popBackStack(graph.startDestination, false)
                }
            }
        }
    }

    fun backPressed(): Boolean =
        getTag(bottomNavigation.selectedItemId)?.let { tag ->
            (fragmentManager.findFragmentByTag(tag) as? NavHostFragment?)?.let { nav ->
                if (nav.childFragmentManager.backStackEntryCount == 0 && bottomNavigation.selectedItemId != mainBottomId) {
                    bottomNavigation.selectedItemId = mainBottomId
                    true
                } else {
                    false
                }
            } ?: false
        } ?: false
}
