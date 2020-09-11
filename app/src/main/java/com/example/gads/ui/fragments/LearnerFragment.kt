package com.example.gads.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gads.Model.LearnerAdapter
import com.example.gads.Model.learner
import com.example.gads.R
import com.example.gads.Services.ApiService
import com.example.gads.Services.ServiceBuilder
import kotlinx.android.synthetic.main.learner_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LearnerFragment : Fragment() {

    private val learnerLayerManager by lazy { LinearLayoutManager(context) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.learner_fragment, container, false)
        return root
    }

    override fun onResume() {
        super.onResume()
        displaylearner()
    }

    private fun displaylearner() {
        learnerlistItem.layoutManager = learnerLayerManager
        val learnerService = ServiceBuilder.buildService(ApiService::class.java)
        val requestCall = learnerService.getLearners()

        requestCall.enqueue(object : Callback<List<learner>> {
            override fun onFailure(call: Call<List<learner>>, t: Throwable) {
                Toast.makeText(context, "Failed To Retrieve Error due to: ${t.toString()}",
                    Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<List<learner>>, response: Response<List<learner>>) {
                if(response.isSuccessful){
                    val learnerList = response.body()
                    val learnerRecyclerAdapter by lazy { learnerList?.let { LearnerAdapter(context!!, it) } }
                    learnerlistItem.adapter = learnerRecyclerAdapter

                }else
                    Toast.makeText(context, "Failed To Retrieve List",
                        Toast.LENGTH_LONG).show()
            }
        })
    }
}