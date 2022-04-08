package com.example.apijsonparsing.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apijsonparsing.adapters.PhotoAdapter
import com.example.apijsonparsing.databinding.ActivityMainBinding
import com.example.apijsonparsing.model.PhotoDataModel
import com.example.apijsonparsing.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ApiClient.instance?.getPhotosList()!!.enqueue(object : Callback<PhotoDataModel> {
            override fun onResponse(
                call: Call<PhotoDataModel>,
                response: Response<PhotoDataModel>
            ) {
                bindMyData(response.body()!!)
                Log.d("TAG", "onFailure: ${response.body()!![0]}")
            }

            override fun onFailure(call: Call<PhotoDataModel>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
            }
        })
    }

    private fun bindMyData(data: PhotoDataModel) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = PhotoAdapter(applicationContext, data)
        binding.recyclerView.adapter = adapter
    }
}