package com.example.hireaskill.Home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hireaskill.R
import com.example.hireaskill.databinding.FragmentAddJobFragmentBinding
import com.example.hireaskill.databinding.FragmentLoginFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Add_Job_fragment : Fragment() {


    private lateinit var binding:FragmentAddJobFragmentBinding
    private lateinit var database:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddJobFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addJobsBtn.setOnClickListener {
            val userid_forjob = FirebaseAuth.getInstance().currentUser?.uid
            val username=binding.txtName.text.toString()
            val Job_Title=binding.txtjobtitle.text.toString()
            val Location=binding.txtlocation.text.toString()
            val Salary=binding.salary.text.toString()
            val Description=binding.txtrequirements.text.toString()

            database=FirebaseDatabase.getInstance().getReference(userid_forjob.toString())
            val userJob=UserJob(username,Job_Title,Location,Salary,Description)
            database.child(Job_Title).setValue(userJob).addOnSuccessListener {
                binding.txtName.text?.clear()
                binding.txtjobtitle.text?.clear()
                binding.txtlocation.text?.clear()
                binding.salary.text?.clear()
                binding.txtrequirements.text?.clear()





            }.addOnFailureListener {




            }

        }


    }

 
}