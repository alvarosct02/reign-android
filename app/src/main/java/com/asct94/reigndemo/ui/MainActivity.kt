package com.asct94.reigndemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.asct94.reigndemo.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfig: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()

    }

    private fun setupViews() {
        navController = findNavController(R.id.nav_host_fragment)

        appBarConfig = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfig)
    }

//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
//    }
//
    override fun onSupportNavigateUp(): Boolean {
//        val currentFragment =
//            (nav_host_fragment.childFragmentManager.fragments[0] as? BaseFragment)
//        if (currentFragment?.onFragmentBackPressed() == true) {
//            return navController.navigateUp(appBarConfig)
//        }
        return navController.navigateUp()
    }
//
//    override fun onBackPressed() {
//        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
//            drawer_layout.closeDrawer(GravityCompat.START)
////        } else if (navController.currentDestination?.id != navController.graph.startDestination) {
////            navController.navigate(navController.graph.startDestination)
//        } else {
//            val currentFragment =
//                (nav_host_fragment.childFragmentManager.fragments[0] as? BaseFragment)
//            if (currentFragment?.onFragmentBackPressed() == true) {
//                if (appBarConfig.topLevelDestinations.contains(navController.currentDestination?.id ?: 0)) {
//                    finish()
//                } else {
//                    super.onBackPressed()
//                }
//            }
//        }
//    }

}