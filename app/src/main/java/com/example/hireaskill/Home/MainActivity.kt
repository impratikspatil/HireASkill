package com.example.hireaskill.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.example.hireaskill.Login.login_fragment
import com.example.hireaskill.R
import com.example.hireaskill.Settings.SettingsActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
        val settings_img= findViewById<ImageView>(R.id.settingsBtn)
        settings_img.setOnClickListener{
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        val myFragment = Jobs_fragment()
        val fragment : Fragment?= supportFragmentManager.findFragmentByTag(Jobs_fragment::class.java.simpleName)

        if(fragment !is Jobs_fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, myFragment).commit()
        }

    }

}