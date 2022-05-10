package com.example.hireaskill.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hireaskill.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*

class ChatListActivity : AppCompatActivity() {
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<UserJob>
    private lateinit var adapter: UserAdapter
    private lateinit var auth: FirebaseAuth
    private var dbRef = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)

        auth = FirebaseAuth.getInstance()

        userList = arrayListOf()
        adapter = UserAdapter(this,userList)

        userRecyclerView = findViewById(R.id.users_rv)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        userRecyclerView.adapter = adapter

        EventChangeListener()
    }

    private fun EventChangeListener() {
        dbRef = FirebaseFirestore.getInstance()
        dbRef.collection("users").addSnapshotListener(object: EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error != null){
                    Log.e("Firestore Error",error.message.toString())
                    return
                }
                for(dc: DocumentChange in value?.documentChanges!!){
                        if (dc.type == DocumentChange.Type.ADDED) {
                            userList.add(dc.document.toObject(UserJob::class.java))
                        }
                }
                adapter.notifyDataSetChanged()
            }

        })

    }


}