package com.example.hireaskill.Home

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.Image
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

class ChatAdapter(private val joblist:ArrayList<Chatdata>,private var ctx: Context) : RecyclerView.Adapter<ChatAdapter.jobviewholder>() {

    class jobviewholder(view: View): RecyclerView.ViewHolder(view)
    {

        val owner: TextView =view.findViewById(R.id.username_recentchat)
        val profile : ImageView = view.findViewById(R.id.profile_recentchat)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): jobviewholder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.recentchatlist, parent, false)

        return jobviewholder(adapterLayout)
    }


    override fun onBindViewHolder(holder: jobviewholder, position: Int) {
        val job = joblist[position]

        val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
        //holder.binding.imageRv.setImageResource(model.image)
        val storageref = FirebaseStorage.getInstance().reference.child("users/$uid.jpg")
        val localFile = File.createTempFile("temp","jpg")
        storageref.getFile(localFile)
       // val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
        //holder.binding.imageRv.setImageBitmap(bitmap)
//        if(job.Profile_Pic!=null){
//            Picasso.get().load(job.Profile_Pic).error(R.drawable.ic_baseline_person_24)
//                .into(holder.profile)
//
//        }


        holder.owner.text=job.username

        holder.itemView.setOnClickListener{
            val intent = Intent(ctx, ChatActivity::class.java)
            intent.putExtra("name",job.username)
            intent.putExtra("user_number",job.number)
            intent.putExtra("uid",job.userid)

            ctx.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return joblist.size
    }

}