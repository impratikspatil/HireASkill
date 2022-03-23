package com.example.hireaskill.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import android.widget.Button
import android.widget.ImageView
import android.widget.Toolbar
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.hireaskill.Login.login_fragment
import com.example.hireaskill.R

import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.hireaskill.Settings.SettingsActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        val navController=findNavController(R.id.fragmentContainerView3)
        val config= AppBarConfiguration(navController.graph)
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).setupWithNavController(navController,config)

        val settings_img= findViewById<ImageView>(R.id.settingsBtn)
        settings_img.setOnClickListener{
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }


        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.btmnav)

        bottomNavigationView.setupWithNavController(navController)


    }

}