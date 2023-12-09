package com.example.player.presentation.adapters

import androidx.recyclerview.widget.AsyncListDiffer
import com.example.player.databinding.SwipeItemBinding
import javax.inject.Inject

class SwipeSongAdapter @Inject constructor() :
    AbstractSongAdapter<SwipeItemBinding>(
        bindingFun = { layoutInflater, viewGroup, b ->
            SwipeItemBinding.inflate(layoutInflater, viewGroup, b)
        }
    ) {

    override var differ = AsyncListDiffer(this, diffCallback)

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val song = songs[position]
        with(binding) {
            val text = "${song.title} - ${song.author}"
            tvPrimary.text = text


            root.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(song)
                }
            }

        }
    }

}