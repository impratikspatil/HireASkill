package com.example.hireaskill.Dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.hireaskill.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.jar.Attributes

class DashboardActivity : AppCompatActivity() {


    var TAG = "DASHBOARD"

    private  lateinit var fauth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        var hey : TextView = findViewById(R.id.hello)
        var wel : TextView = findViewById(R.id.welcome)

        var id = FirebaseAuth.getInstance().currentUser?.uid.toString()

        FirebaseFirestore.getInstance().collection("users").get()
            .addOnCompleteListener() {
                val result : StringBuffer = StringBuffer()
                if(it.isSuccessful){
                    for(document in it.result){
                            var it_id = document.get("user_UID")

                            if(it_id.toString()==id) {
                                result.append("Hello,").append(document.data.getValue("Name"))
                            }
                        }
                    hey.setText(result)
                    wel.setText("Welcome To HireASkill")
            }







    }
}}