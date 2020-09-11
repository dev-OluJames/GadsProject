package com.example.gads.Model

import android.net.Uri
import retrofit2.http.Url

data class skill (var name:String,
                    var score:Int,
                    var country:String,
                    var badgeUrl:String?=null)
data class learner (var name:String,
                    var hours:Int,
                    var country:String,
                    var badgeUrl: String?=null)
data class submit (var email:String?=null,
                    var firstName:String?=null,
                    var lastName:String?=null,
                    var link: String?=null)