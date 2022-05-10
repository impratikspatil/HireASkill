package com.example.hireaskill.Home

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hireaskill.R
import com.google.firebase.firestore.auth.User

class JobAdapter(private val joblist:ArrayList<UserJob>,private var ctx: Context) : RecyclerView.Adapter<JobAdapter.jobviewholder>() {

    class jobviewholder(view: View): RecyclerView.ViewHolder(view)
    {
       val jobtitle:TextView=view.findViewById(R.id.jobtitle)
       val salary:TextView=view.findViewById(R.id.salary)
       val location:TextView=view.findViewById(R.id.location)
        val owner:TextView=view.findViewById(R.id.owner)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): jobviewholder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.add_job_items, parent, false)

        return jobviewholder(adapterLayout)
    }


    override fun onBindViewHolder(holder: jobviewholder, position: Int) {
        val job = joblist[position]


        holder.jobtitle.text=job.jobt
        holder.salary.text=job.jobsal
        holder.location.text=job.jobloc
        holder.owner.text=job.username

        holder.itemView.setOnClickListener{
            val intent = Intent(ctx, ChatActivity::class.java)
            intent.putExtra("name",job.username)
            intent.putExtra("uid",job.userid)

            ctx.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return joblist.size
    }

}