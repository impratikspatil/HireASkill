package com.example.hireaskill.Settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hireaskill.Home.JobAdapter
import com.example.hireaskill.Home.UserJob
import com.example.hireaskill.R
import com.example.hireaskill.databinding.FragmentJobsFragmentBinding
import com.example.hireaskill.databinding.FragmentMentionedJobsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class MentionedJobsFragment : Fragment() {

    val TAG = "Jobs_fragment"

    private lateinit var binding : FragmentMentionedJobsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var datalist: ArrayList<UserJob>
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        datalist = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMentionedJobsBinding.inflate(layoutInflater)
        recyclerView = binding.jobsMentionedId
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userid = FirebaseAuth.getInstance().currentUser!!.uid

        dbRef = FirebaseDatabase.getInstance().getReference("Jobs").child(userid)
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()

                if(snapshot.exists()){
                    for(jobdata in snapshot.children){

                        val data = jobdata.getValue(UserJob::class.java)

                        datalist.add(data!!)
                    }
                    val jobadapter = JobAdapter(datalist,requireContext())
                    recyclerView.adapter = jobadapter

                    recyclerView.visibility = View.VISIBLE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}