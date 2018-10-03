package com.toddywannalearn.retrofit2sample

import com.google.gson.annotations.SerializedName

class Post {

    var userId: Int = 0
    var id: Int = 0
    var title: String = ""

    @SerializedName("body")
    var text: String = ""
}