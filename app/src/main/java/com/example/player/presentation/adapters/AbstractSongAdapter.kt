package com.example.player.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.player.data.entities.Song

abstract class AbstractSongAdapter<T : ViewBinding> constructor(
    private inline val bindingFun: (LayoutInflater, ViewGroup, Boolean) -> T,
) : RecyclerView.Adapter<AbstractSongAdapter.Holder>() {

    protected lateinit var binding: T

    protected val diffCallback = object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.mediaId == newItem.mediaId
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    protected abstract var differ: AsyncListDiffer<Song>

    var songs: List<Song>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun getItemCount(): Int = songs.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = bindingFun(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }


    protected var onItemClickListener: ((Song) -> Unit)? = null

    fun setItemClickListener(listener: (Song) -> Unit) {
        onItemClickListener = listener
    }


    class Holder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)
}