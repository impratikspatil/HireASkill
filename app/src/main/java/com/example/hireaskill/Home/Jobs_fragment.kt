package com.example.hireaskill.Home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hireaskill.R
import com.example.hireaskill.databinding.FragmentJobsFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.auth.User


class Jobs_fragment : Fragment() {

    val TAG = "Jobs_fragment"

    private lateinit var binding : FragmentJobsFragmentBinding
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
        binding = FragmentJobsFragmentBinding.inflate(layoutInflater)
        recyclerView = binding.jobsRv
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        return binding.root
        //Inflate the layout for this fragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController=findNavController()

        binding.floatbtn.setOnClickListener {

            navController.navigate(R.id.action_jobs_fragment_to_add_Job_fragment)


        }

        val userid = FirebaseAuth.getInstance().currentUser!!.uid

        dbRef = FirebaseDatabase.getInstance().getReference("Jobs")
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()

                if(snapshot.exists()){
                    for(jobdata in snapshot.children){

                        val data = jobdata.getValue(object : GenericTypeIndicator<HashMap<String,HashMap<String,String>>>() {})
                        for(set in data!!.values){
                            val da = mutableListOf<String>()
                            da.clear()
                            for(dat in set.values){
                                da.add(dat.toString())
                            }

                            val abc = UserJob(da[0],da[4],da[1],da[3],da[5],da[2])
                            datalist.add(abc)



                            //datalist.add(dat!!)
                        }
//                        val data = jobdata.getValue(UserJob::class.java)
//                        datalist.add(data!!)

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