package com.example.hireaskill.Login
import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace

import com.example.hireaskill.databinding.FragmentSignupFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase


class signup_fragment : Fragment() {

    private lateinit var fireAuth : FirebaseAuth
    private lateinit var binding :FragmentSignupFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignupFragmentBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fireAuth= FirebaseAuth.getInstance()

        var button : Button = view.findViewById(com.example.hireaskill.R.id.registerNow)
        var name : EditText = view.findViewById(com.example.hireaskill.R.id.txtName)
        var email : EditText = view.findViewById(com.example.hireaskill.R.id.txtEmail)
        var pass1 : EditText = view.findViewById(com.example.hireaskill.R.id.txtPass)
        var pass2 : EditText = view.findViewById(com.example.hireaskill.R.id.txtPassConfirm)

        button.setOnClickListener{
            if(name.text.isEmpty()){
                name.error="Name can not be empty!"
                name.requestFocus()
            }
            else if(email.text.toString().isEmpty()){
                email.error="Email can not be empty!"
                email.requestFocus()
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
                email.error="Invalid email format!"
                email.requestFocus()

            }
            else if(pass1.text.toString().isEmpty()){
                pass1.error="Password can not be empty!"
                pass1.requestFocus()
            }
            else if(pass1.text.toString().length<6){
                pass1.error="Password length should be at least 6!"
                pass1.requestFocus()
            }
            else if(pass2.text.toString().isEmpty()){
                pass2.error="Confirm password can not be empty!"
                pass2.requestFocus()
            }

            else if(pass2.text.toString()!=pass1.text.toString()){
                pass2.error="Password mismatched!"
                pass2.requestFocus()
            }


            else {
                fireAuth.createUserWithEmailAndPassword(email.text.toString(),pass1.text.toString()).addOnSuccessListener {

                    val user : MutableMap<String,Any> = HashMap()
                    val db = FirebaseFirestore.getInstance()
                    user["username"]=name.text.toString()
                    user["email"]=email.text.toString()
                    user["number"]=""
                    user["profile_url"]=""
                    user["id"]=FirebaseAuth.getInstance().currentUser?.uid.toString()

                    Firebase.auth.uid?.let { it1 ->
                        db.collection("users").document(it1).set(user, SetOptions.merge())
                    }



                    Toast.makeText(context, "ACCOUNT CREATED SUCCESSFULLY", Toast.LENGTH_SHORT).show()

                    val fragmentlogin=login_fragment()

                    requireActivity().supportFragmentManager.beginTransaction().replace(com.example.hireaskill.R.id.fragmentContainerView,login_fragment()).commit()

                }.addOnFailureListener{e->
                    Toast.makeText(context, "ACCOUNT CREATION FAILED DUE TO $e", Toast.LENGTH_SHORT).show()

                }

            }
        }

        //changed this code

         val fragmentlogin=login_fragment()

        //changed this code
        binding.login.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().replace(com.example.hireaskill.R.id.fragmentContainerView,fragmentlogin).commit()

        }

    }




    }


