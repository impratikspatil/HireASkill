package com.example.hireaskill.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.hireaskill.databinding.FragmentLoginFragmentBinding

class login_fragment : Fragment() {
    private lateinit var binding :FragmentLoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerNow.setOnClickListener{
            Toast.makeText(context,"REGISTER_NOW",Toast.LENGTH_SHORT).show()
        }
        binding.button.setOnClickListener{
            Toast.makeText(context,"LOGIN",Toast.LENGTH_SHORT).show()

        }
    }
}