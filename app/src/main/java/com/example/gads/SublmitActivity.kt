package com.example.gads

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
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

        submission.setOnClickListener {
            val mDialogueView= LayoutInflater.from(this)
                .inflate(R.layout.control_panel, null)
            val mBuilder = AlertDialog.Builder(this).setView(mDialogueView).show()

            mDialogueView.ok_button.setOnClickListener {
                mBuilder.dismiss()
                projectSubmission()
            }

            mDialogueView.imageView2.setOnClickListener {
                mBuilder.dismiss()
            }
        }


    }



    fun projectSubmission(){
        var firstName = first_name.text.toString()
        var lastName = last_name.text.toString()
        var email = email.text.toString()
        var link = github_link.text.toString()

        val serviceBuilder = ServiceBuilder.buildService(ApiService::class.java)
        val requestCall = serviceBuilder.submitProject(
                    email,
                    firstName,
                    lastName,
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
                        }else
                            Toast.makeText(this@SublmitActivity, "see the response: ${response}", Toast.LENGTH_LONG).show()


                    }

                })


    }


}