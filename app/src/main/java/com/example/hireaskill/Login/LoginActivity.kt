package com.example.hireaskill.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.hireaskill.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
       val myFragment =login_fragment()
        val fragment : Fragment?= supportFragmentManager.findFragmentByTag(login_fragment::class.java.simpleName)

       if(fragment !is login_fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, myFragment).commit()
        }
    }



}