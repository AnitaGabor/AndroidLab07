


package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.quizapp.fragments.*
import com.example.quizapp.repository.Repository
import com.example.quizapp.viewModel.MainViewModel
import com.example.quizapp.viewModel.MainViewModelFactory

import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.myNavHostFragment)
        val navigationView = findViewById<NavigationView>(R.id.nav_drawer)
        navigationView?.setupWithNavController(navController)

        //NavigationUI.setupActionBarWithNavController(this,navController)

        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        topAppBar.setNavigationOnClickListener {
            drawerLayout.open()
        }
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.close()
            true
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    findNavController(R.id.myNavHostFragment).navigate(R.id.homeFragment)
                }
                R.id.nav_quizTime -> {
                    Navigation.findNavController(this,R.id.myNavHostFragment).navigate(R.id.quizStartFragment)
                }
                R.id.nav_profile -> {
                    Navigation.findNavController(this,R.id.myNavHostFragment).navigate(R.id.profileFragment)
                }
                R.id.nav_list -> {
                    Navigation.findNavController(this,R.id.myNavHostFragment).navigate(R.id.listFragment)
                }
                R.id.nav_add -> {
                    Navigation.findNavController(this,R.id.myNavHostFragment).navigate(R.id.addFragment)
                }

            }

            drawerLayout.close()
            menuItem.isChecked=true
            true
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}