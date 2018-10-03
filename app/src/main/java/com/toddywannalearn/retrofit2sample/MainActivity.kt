package com.toddywannalearn.retrofit2sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var txtResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtResult = findViewById(R.id.txt_result)

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val jsonPlaceHolder: JsonPlaceHolder = retrofit.create(JsonPlaceHolder::class.java)

        var call: Call<List<Post>> = jsonPlaceHolder.getPosts()

        call.enqueue(object: Callback<List<Post>>{
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                txtResult.text = t.message
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if(!response.isSuccessful){
                    txtResult.text = "Code: ${response.code()}"
                    return
                }

                var posts: List<Post> = response.body()!!

                for(post in posts){
                    var content = ""
                    content += "id: ${post.id} \n"
                    content += "user id: ${post.userId} \n"
                    content += "title: ${post.title} \n"
                    content += "text: ${post.text} \n\n"

                    txtResult.append(content)
                }
            }
        })

    }
}
