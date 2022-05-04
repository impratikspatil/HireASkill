package com.example.hireaskill.Settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.hireaskill.Home.MainActivity
import com.example.hireaskill.Login.LoginActivity
import com.example.hireaskill.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(findViewById(R.id.toolbar_setting))
        val navController=findNavController(R.id.Navhost_settings)
        val config= AppBarConfiguration(navController.graph)
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_setting).setupWithNavController(navController,config)







    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))

    }

}