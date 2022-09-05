package com.example.todoappclean

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(navHostController())


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController: NavController = navHostController()
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun navHostController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        return navHostFragment.navController
    }
}