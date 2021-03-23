package com.example.newsfresh

import com.example.newsfresh.MySingleton.MySingleton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest



import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsItemClicked {
    private lateinit var mAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()

        mAdapter = NewsListAdapter(this)
        recyclerView.adapter = mAdapter
    }
    private fun fetchData(){
        val url = "http://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=21655e0e3d49445f908b90a3582e9c57"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener {
              val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for(i in 0 until newsJsonArray.length()){
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")

                    )
                    newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            },
            Response.ErrorListener {
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_LONG).show()
            })

// Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
}

    override fun onItemClicked(item: News) {

    }
}