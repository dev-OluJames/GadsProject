package com.example.gads.Model

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gads.R
import retrofit2.http.Url

class skillAdapter(private val context: Context, private val skills: List<skill>) :
    RecyclerView.Adapter<skillAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_skill_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = skills.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val skill = skills[position]
        holder.textName?.text = skill.name
        holder.textScore?.text = skill.score.toString()
        holder.textCountry?.text = skill.country
        //val badge: Url? = skill.badgeUrl
        //Glide.with(context).load(badge).into(holder.badgeImage)
        holder.notePosition = position
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView?.findViewById<TextView>(R.id.textName)
        val textScore = itemView?.findViewById<TextView>(R.id.textScore)
        val textCountry = itemView?.findViewById<TextView>(R.id.textCountry)
        val badgeImage: ImageView = itemView.findViewById(R.id.imageView)

        var notePosition = 0

    }
}