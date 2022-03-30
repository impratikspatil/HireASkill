package com.example.hireaskill.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hireaskill.R

class JobAdapter(private val joblist:ArrayList<jobs_data>) : RecyclerView.Adapter<JobAdapter.jobviewholder>() {

    class jobviewholder(view: View): RecyclerView.ViewHolder(view)
    {
       val jobtitle:TextView=view.findViewById(R.id.jobtitle)
       val salary:TextView=view.findViewById(R.id.salary)
       val ownerimage:ImageView=view.findViewById(R.id.ownerimg)
       val owner:TextView=view.findViewById(R.id.owner)
       val icon:ImageView=view.findViewById(R.id.loc_icon)
       val location:TextView=view.findViewById(R.id.location)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): jobviewholder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.joblist_card, parent, false)

        return jobviewholder(adapterLayout)
    }


    override fun onBindViewHolder(holder: jobviewholder, position: Int) {
        val job:jobs_data = joblist[position]

        holder.jobtitle.text=job.job_title
        holder.salary.text=job.salary
        holder.owner.text=job.owner_name
        holder.location.text=job.location


    }

    override fun getItemCount(): Int {
        return joblist.size
    }

}