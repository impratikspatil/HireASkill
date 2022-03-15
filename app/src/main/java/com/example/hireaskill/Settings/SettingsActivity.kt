package com.example.hireaskill.Settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.hireaskill.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val myFragment =Settings_Fragment()
        val fragment : Fragment?= supportFragmentManager.findFragmentByTag(Settings_Fragment::class.java.simpleName)

        if(fragment !is Settings_Fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, myFragment).commit()
        }
    }
}