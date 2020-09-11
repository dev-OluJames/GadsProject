package com.example.gads

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.gads.Services.ApiService
import com.example.gads.Services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_sublmit.*
import kotlinx.android.synthetic.main.control_panel.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SublmitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sublmit)

        toolbar.setNavigationOnClickListener { view -> onBackPressed() }

    }

    override fun onResume() {
        super.onResume()
        projectSubmission()
    }

    fun projectSubmission(){
        var firstName = first_name.text.toString()
        var lastName = last_name.text.toString()
        var email = email.toString()
        var link = github_link.toString()

        val serviceBuilder = ServiceBuilder.buildService(ApiService::class.java)

        submission.setOnClickListener {
            val mDialogueView= LayoutInflater.from(this)
                .inflate(R.layout.control_panel, null)
            val mBuilder = AlertDialog.Builder(this).setView(mDialogueView).show()

            mDialogueView.ok_button.setOnClickListener {
                mBuilder.dismiss()
                val requestCall = serviceBuilder.submitProject(
                    firstName,
                    lastName,
                    email,
                    link)
                requestCall.enqueue(object : Callback<Void>{
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        val mDialog = LayoutInflater.from(this@SublmitActivity)
                            .inflate(R.layout.warning_panel, null)
                        val mBuilder = AlertDialog.Builder(this@SublmitActivity)
                            .setView(mDialog).show()
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if(response.isSuccessful){
                            val mDialogue = LayoutInflater.from(this@SublmitActivity).
                            inflate(R.layout.success_panel, null)
                            val mBuilder = AlertDialog.Builder(this@SublmitActivity).
                            setView(mDialogue).show()
                        }


                    }

                })
            }

            mDialogueView.imageView2.setOnClickListener {
                mBuilder.dismiss()
            }
        }

    }


}