package com.example.hireaskill.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hireaskill.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatActivity : AppCompatActivity() {
    private lateinit var chatRecyclerView : RecyclerView
    private lateinit var messageBox : EditText
    private lateinit var sendButton : ImageView
//    private lateinit var userAdapter : RecyclerView
    private lateinit var messageList: ArrayList<MessageModal>
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var dbRef : DatabaseReference

    var receiverRoom:String?= null
    var senderRoom:String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setSupportActionBar(findViewById(R.id.toolbar_chat))

        val name = intent.getStringExtra("name")
        val receiverUid = intent.getStringExtra("uid")
        val senderUid = FirebaseAuth.getInstance().currentUser?.uid

        dbRef = FirebaseDatabase.getInstance().getReference()
        senderRoom = receiverUid.toString() + senderUid.toString()
        receiverRoom = senderUid.toString() + receiverUid.toString()

        Log.d("TAG","$senderRoom    ->  $receiverRoom   ->$senderUid ->$receiverUid")
        chatRecyclerView = findViewById(R.id.chatRecyclerView)
        messageBox = findViewById(R.id.messageBox)
        sendButton= findViewById(R.id.sentButton)
        messageList = ArrayList()
        messageAdapter = MessageAdapter(this,messageList)

        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter=messageAdapter


        supportActionBar?.title=name


        dbRef.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                messageList.clear()
                for(postSnapshot in snapshot.children){
                    val message = postSnapshot.getValue(MessageModal::class.java)
                    messageList.add(message!!)
                }
                messageAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        sendButton.setOnClickListener {
            val chat = HashMap<String,Any>()
            val message = messageBox.text.toString()
            if (receiverUid != null) {
                chat.put(receiverUid, 1)
            }
            if (senderUid != null) {
                chat.put(senderUid,1)
            }
            if (message == "") {
                messageBox.error = "Message can not be empty!!!"
                messageBox.requestFocus()
            } else {
                val messageObject = MessageModal(message, senderUid, receiverUid)

                dbRef.child("chats").child(senderRoom!!).child("messages").push()
                    .setValue(messageObject).addOnSuccessListener {
                        dbRef.child("chats").child(receiverRoom!!).child("messages").push()
                            .setValue(messageObject)
                        dbRef.child("RecentChats").child(senderUid!!).child(senderUid).updateChildren(chat)
                        dbRef.child("RecentChats").child(receiverUid!!).child(receiverUid).updateChildren(chat)
                    }
                messageBox.setText("")
            }



        }
    }
}