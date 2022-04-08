package com.example.apijsonparsing.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apijsonparsing.databinding.LayoutRecyclerviewBinding
import com.example.apijsonparsing.model.PhotoDataModel
import com.squareup.picasso.Picasso

class PhotoAdapter(private val context: Context, private val data: PhotoDataModel) : RecyclerView.Adapter<PhotoAdapter.MyViewHolder>() {
    private lateinit var binding: LayoutRecyclerviewBinding

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = LayoutRecyclerviewBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        binding.albumId.text = data[position].albumId.toString()
        binding.id.text = data[position].id.toString()
        binding.title.text = data[position].title.toString()

        Picasso.get().load(data[position].url).into(binding.imageUrl)
        Picasso.get().load(data[position].thumbnailUrl).into(binding.imageThumbnailUrl)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}