package com.example.hireaskill.Home

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.example.hireaskill.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var lottieAnimationView=findViewById<LottieAnimationView>(R.id.lottie_anim)
        var backgroundimage=findViewById<LottieAnimationView>(R.id.backgroundimg)
        var textview=findViewById<TextView>(R.id.texthire)

        textview.animate().translationY(3500f).setDuration(1000).setStartDelay(5000)

        backgroundimage.animate().translationY(-3500f).setDuration(1000).startDelay = 5000
        lottieAnimationView.animate().translationY(3500f).setDuration(1000).setStartDelay(5000)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    val intent = Intent(this@SplashScreen,MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })


    }}