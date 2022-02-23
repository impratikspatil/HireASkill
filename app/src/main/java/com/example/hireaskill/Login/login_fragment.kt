package com.example.hireaskill.Login
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hireaskill.R
import com.example.hireaskill.databinding.FragmentLoginFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class login_fragment : Fragment() {


    private lateinit var binding :FragmentLoginFragmentBinding
    private lateinit var fireAuth : FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fireAuth= FirebaseAuth.getInstance()
        val fragmentsignup=signup_fragment()

        //changed this code
        binding.registerNow.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,fragmentsignup).commit()

        }

        binding.button.setOnClickListener{
            if(binding.EmailText.text.isEmpty()){
                binding.EmailText.error="Email can not be empty!"
                binding.EmailText.requestFocus()
            }
            else if(binding.PasswordText.text.toString().isEmpty()){
                binding.PasswordText.error="Password can not be empty!"
                binding.PasswordText.requestFocus()
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(binding.EmailText.text.toString()).matches()){
                binding.EmailText.error="Invalid email format!"
                binding.EmailText.requestFocus()

            }
            else {
                login()

            }
        }
    }


    private fun login() {
        fireAuth.signInWithEmailAndPassword(binding.EmailText.text.toString(),binding.PasswordText.text.toString()).addOnSuccessListener {
            Toast.makeText(context, "LOGGED IN SUCCESSFULLY", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{e->
            Toast.makeText(context, "LOGGED IN FAILED DUE TO $e", Toast.LENGTH_SHORT).show()

        }
    }


}