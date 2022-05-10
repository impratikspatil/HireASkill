package com.example.hireaskill.Home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hireaskill.R
import com.example.hireaskill.databinding.FragmentUserSkillFragmentBinding
import com.example.hireaskill.databinding.FragmentUserSkillsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class User_skill_fragment : Fragment() {

    private lateinit var binding: FragmentUserSkillFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var datalist: ArrayList<UserData>
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
        binding = FragmentUserSkillFragmentBinding.inflate(layoutInflater)
        recyclerView = binding.userskillRv
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController=findNavController()
        binding.floatUserBtn.setOnClickListener {

            navController.navigate(R.id.action_user_skill_fragment_to_userSkillsFragment)
        }

        val userid = FirebaseAuth.getInstance().currentUser!!.uid

        dbRef = FirebaseDatabase.getInstance().getReference("UserSkill")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()

                if(snapshot.exists()){
                    for(jobdata in snapshot.children){

                        val data = jobdata.getValue(object : GenericTypeIndicator<HashMap<String, HashMap<String, String>>>() {})
                        for(set in data!!.values){
                            val da = mutableListOf<String>()
                            da.clear()


                            for(dat in set.values){
                                da.add(dat.toString())
                            }
                            if(da[1] != userid) {
                                Log.d("TASDF", "$da")
                                val abc = UserData(da[4], da[3], da[0], da[2],da[1])
                                datalist.add(abc)
                            }



                            //datalist.add(dat!!)
                        }
//                        val data = jobdata.getValue(UserJob::class.java)
//                        datalist.add(data!!)

                    }
                    val jobadapter = SkillAdapter(datalist,requireContext())
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