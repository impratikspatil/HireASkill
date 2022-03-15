package com.example.hireaskill.Login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.window.SplashScreen
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hireaskill.Home.MainActivity
import com.example.hireaskill.R
import com.google.firebase.auth.FirebaseAuth


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

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            // User is signed in
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        } else {
            // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out")
        }

    }

    override fun onBackPressed() {
        startActivity(Intent(this, LoginActivity::class.java))
        moveTaskToBack(true)
    }



}