package com.example.player.presentation.adapters

import androidx.recyclerview.widget.AsyncListDiffer
import com.bumptech.glide.RequestManager
import com.example.player.databinding.ListItemBinding
import javax.inject.Inject

class SongAdapter @Inject constructor(
    private val glide: RequestManager
) :
    AbstractSongAdapter<ListItemBinding>(
        bindingFun = { layoutInflater, viewGroup, b ->
            ListItemBinding.inflate(layoutInflater, viewGroup, b)
        }
    ) {

    override var differ = AsyncListDiffer(this, diffCallback)

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val song = songs[position]
        with(binding) {
            tvPrimary.text = song.title
            tvSecondary.text = song.author
            glide.load(song.imageUrl).into(ivItemImage)

            root.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(song)
                }
            }
        }
    }

}