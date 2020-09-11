package com.example.gads.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gads.Model.DataManager
import com.example.gads.Model.skill
import com.example.gads.Model.skillAdapter
import com.example.gads.R
import com.example.gads.Services.ApiService
import com.example.gads.Services.ServiceBuilder
import kotlinx.android.synthetic.main.skill_fragment.*
import kotlinx.android.synthetic.main.skill_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SkillFragment : Fragment() {


    private val skillLayerManager by lazy { LinearLayoutManager(context) }
    private val skillRecyclerAdapter by lazy {skillAdapter(context!!, DataManager.skills)}

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.skill_fragment, container, false)
        return root
    }

    override fun onStart() {
        super.onStart()
        displaySkill()
    }

    private fun displaySkill() {
        skillistItem.layoutManager = skillLayerManager
        val skillService = ServiceBuilder.buildService(ApiService::class.java)
        val requestCall = skillService.getSkills()

        requestCall.enqueue(object : Callback<List<skill>> {
            override fun onFailure(call: Call<List<skill>>, t: Throwable) {
                Toast.makeText(context, "Failed To Retrieve Error due to: ${t.toString()}",
                    Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<skill>>, response: Response<List<skill>>) {
                if(response.isSuccessful){
                    val skillList = response.body()
                    val skillRecyclerAdapter by lazy { skillList?.let { skillAdapter(context!!, it) } }
                    skillistItem.adapter = skillRecyclerAdapter


                }else
                    Toast.makeText(context, "Failed To Retrieve List",
                        Toast.LENGTH_LONG).show()
            }
        })


    }

}