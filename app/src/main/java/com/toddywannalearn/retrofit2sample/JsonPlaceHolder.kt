package com.toddywannalearn.retrofit2sample

import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolder {

    @GET("posts")
    fun getPosts(): Call<List<Post>>

}