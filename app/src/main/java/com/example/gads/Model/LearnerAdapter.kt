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

class LearnerAdapter(private val context: Context, private val learners: List<learner>) :
    RecyclerView.Adapter<LearnerAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_learner_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = learners.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val learner = learners[position]
        holder.textName?.text = learner.name
        holder.textScore?.text = learner.hours.toString()
        holder.textCountry?.text = learner.country
        //val badge: Url? = learner.badgeUrl
        //Glide.with(context).load(badge).into(holder.badgeImage)
        holder.notePosition = position
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView?.findViewById<TextView>(R.id.learnerName)
        val textScore = itemView?.findViewById<TextView>(R.id.learnerHour)
        val textCountry = itemView?.findViewById<TextView>(R.id.learnerCountry)
        val badgeImage: ImageView = itemView.findViewById(R.id.learnerImageView)
        var notePosition = 0

    }
}