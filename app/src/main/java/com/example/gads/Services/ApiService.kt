package com.example.gads.Services

import com.example.gads.Model.learner
import com.example.gads.Model.skill
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("api/hours")
    fun getLearners(): Call<List<learner>>

    @GET("api/skilliq")
    fun getSkills(): Call<List<skill>>

    @FormUrlEncoded
    @POST("https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    fun submitProject(@Field("entry.1877115667  ") first_name:String,
                      @Field("entry.2006916086") last_name:String,
                      @Field("entry.1824927963  ") email: String,
                      @Field("entry.284483984") link:String
    ): Call<Void>
}