package com.example.parcialtp3.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.MenuProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.parcialtp3.R
import com.example.parcialtp3.databinding.ActivityMainBinding
import com.example.parcialtp3.ui.settings.SettingsActivity
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: NavigationView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var listener: SharedPreferences.OnSharedPreferenceChangeListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("darkMode", false)

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout

        val navView: NavigationView = binding.navView

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.favouritesFragment,
                R.id.adoptionFragment,
                R.id.recyclerView,
                R.id.publicationFragment,
            ), drawerLayout
        )

        val bottomNavigationView = binding.appBarMain.contentMain.bottomBar

        setupActionBarWithNavController(navController, appBarConfiguration)

        setupWithNavController(bottomNavigationView, navController).also {
            setupWithNavController(navView, navController)
        }

        addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean = when (menuItem.itemId) {

                android.R.id.home -> navController.navigateUp(appBarConfiguration)
                else -> true
            }

        })

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.configuration -> {
                    Log.d(TAG, "configuration!")
                    val intent = Intent(this@MainActivity, SettingsActivity::class.java)
                    startActivity(intent)
                }

                else -> {
                    NavigationUI.onNavDestinationSelected(it, navController)
                    drawerLayout.closeDrawers()
                }
            }
            true
        }


        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
            if (nd.id == nc.graph.startDestinationId) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }


        listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == "ProfileImage") {
                val imageUrl = sharedPreferences.getString("ProfileImage", null)
                if (imageUrl != null) {
                    val navView: NavigationView = binding.navView
                    val headerView = navView.getHeaderView(0)
                    val navImageView = headerView.findViewById<ImageView>(R.id.profileImage)
                    Picasso.get()
                        .load(imageUrl)
                        .into(navImageView)
                }
            }
        }

        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        binding.drawerLayout.closeDrawers()
        // Clear the selected item in the navigation drawer
        val navigationView =
            binding.navView// Replace 'navigationView' with the actual ID of your navigation view
        val menu = navigationView.menu

        for (i in 0 until menu.size()) {
            menu.getItem(i).isChecked = false
        }
//con eso muestro la imagen de usuario en el header
        val navView: NavigationView = binding.navView
        val headerView = navView.getHeaderView(0)
        val navImageView = headerView.findViewById<ImageView>(R.id.profileImage)

        val sharedPreferences = getSharedPreferences("ProfilePreferences", Context.MODE_PRIVATE)
        val imageUrl = sharedPreferences.getString("ProfileImage", null)

        if (imageUrl != null) {
            Picasso.get()
                .load(imageUrl)
                .into(navImageView)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}