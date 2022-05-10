package com.example.hireaskill.Home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hireaskill.databinding.FragmentChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore


class ChatFragment : Fragment() {

    private lateinit var binding : FragmentChatBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var datalist: ArrayList<Chatdata>
    private lateinit var messageList: ArrayList<MessageModal>
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var mAuth: FirebaseAuth
    private lateinit var dbRef : DatabaseReference

    var senderRoom:String?= null

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
        binding = FragmentChatBinding.inflate(layoutInflater)
        recyclerView = binding.chatsRv
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        return binding.root
        //Inflate the layout for this fragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController=findNavController()
        val da = mutableListOf<String>()

        val userid = FirebaseAuth.getInstance().currentUser!!.uid
        dbRef = FirebaseDatabase.getInstance().getReference()


        val list = mutableListOf<String>()
        list.clear()



        dbRef = FirebaseDatabase.getInstance().getReference("RecentChats").child(userid.toString())
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (jobdata in snapshot.children) {

                        val data = jobdata.getValue(object :
                            GenericTypeIndicator<HashMap<String, Any>>() {})

                        for (set in data!!.keys) {
                            if (set != userid) {
                                list.add(set.toString())
                                Log.d("TAAA", "${data.keys}")
                            }
                        }
                    }
                }
                Log.d("TABC", "${list}")
            }

            override fun onCancelled(error: DatabaseError) {

            }




        })

//        dbRef.child("RecentChats").child(userid!!).child(userid)
//            .addValueEventListener(object: ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    messageList.clear()
//                    for(postSnapshot in snapshot.children){
//                        val message = postSnapshot.getValue(MessageModal::class.java)
//                        messageList.add(message!!)
//                    }
//                    messageAdapter.notifyDataSetChanged()
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                }
//            })


            var db = FirebaseFirestore.getInstance()
            db.collection("users")
                .get()
                .addOnSuccessListener {   task ->
//                    if (task.isSuccessful) {
//
//                        val document = task.result
//                        if (document != null) {
////                        if(document["id"].toString() in list) {
////                            val id = document.documents
////                           // val name = document.getString("username")
////
////                           // datalist.add(Chatdata(name,id))
////                        }
//
//                        }
                    for(result in task) {

                        if(result.id in list){
                            datalist.add(Chatdata(result.get("username").toString(),result.id,result.get("profile_url").toString(),result.get("number").toString()))
                        }
                    }
                    Log.d("SHRE","$datalist")
                        val jobadapter = ChatAdapter(datalist, requireContext())
                        recyclerView.adapter = jobadapter

                        recyclerView.visibility = View.VISIBLE
                    }

        }
    }

