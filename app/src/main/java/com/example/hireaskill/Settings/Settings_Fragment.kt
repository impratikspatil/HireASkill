package com.example.hireaskill.Settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hireaskill.Login.LoginActivity
import com.example.hireaskill.databinding.FragmentSettingsBinding
import com.google.firebase.auth.FirebaseAuth


class Settings_Fragment : Fragment() {

    private lateinit var binding : FragmentSettingsBinding
    private lateinit var fireAuth : FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fireAuth= FirebaseAuth.getInstance()
        binding.logoutBtn.setOnClickListener(View.OnClickListener {
            if (fireAuth.currentUser != null) fireAuth.signOut()
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        })
        }

}