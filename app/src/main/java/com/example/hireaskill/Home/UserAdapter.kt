package com.example.hireaskill.Home

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hireaskill.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.io.File

class UserAdapter(val context: Context, private val userList: ArrayList<UserJob>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>()  {

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textName: TextView = itemView.findViewById(R.id.user_name)
        val profilePhoto: ImageView = itemView.findViewById(R.id.image_rv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false)
        return UserViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.textName.text = currentUser.username

        val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
//        val storageref = FirebaseStorage.getInstance().reference.child("Users/$uid.jpg")
//        val localFile = File.createTempFile("temp","jpg")
//        storageref.getFile(localFile)
//        val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)


        holder.itemView.setOnClickListener{
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("name",currentUser.username)
            intent.putExtra("uid",currentUser.userid)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}