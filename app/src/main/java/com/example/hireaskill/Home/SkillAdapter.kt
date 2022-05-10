package com.example.hireaskill.Home

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hireaskill.R
import com.google.firebase.firestore.auth.User

class SkillAdapter(private val joblist:ArrayList<UserData>,private var ctx: Context) : RecyclerView.Adapter<SkillAdapter.skillviewholder>() {

    class skillviewholder(view: View): RecyclerView.ViewHolder(view)
    {
        val jobtitle:TextView=view.findViewById(R.id.user_name)
        //val description:TextView=view.findViewById(R.id.Description)
        val location:TextView=view.findViewById(R.id.locationuser)
        val owner:TextView=view.findViewById(R.id.user_skill)
        val chatbutton :Button = view.findViewById(R.id.Chat_button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): skillviewholder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.joblist_card, parent, false)

        return skillviewholder(adapterLayout)
    }

    override fun onBindViewHolder(holder: skillviewholder, position: Int) {
        val job = joblist[position]


        holder.jobtitle.text=job.jobt
        holder.location.text=job.jobloc
        holder.owner.text=job.username
       // holder.description.text = job.jobdes

        holder.chatbutton.setOnClickListener{
            val intent = Intent(ctx, ChatActivity::class.java)
            intent.putExtra("name",job.username)
            intent.putExtra("uid",job.userid)
            Log.d("VISHA" ,"${job.userid}")
            ctx.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return joblist.size
    }

}