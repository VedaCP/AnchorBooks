package com.example.anchorbooks

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anchorbooks.databinding.ItemBooksBinding

class AdapterBooks: RecyclerView.Adapter<AdapterBooks.BooksVH> () {

    private var listAdapterBooks = listOf<AnchorBooksEntity>()

    private var booksItem = MutableLiveData<AnchorBooksEntity>()

    fun booksItem(): LiveData<AnchorBooksEntity> = booksItem

    fun update(list: List<AnchorBooksEntity>) {
        listAdapterBooks = list
        notifyDataSetChanged()
    }
    inner class BooksVH(private val binding: ItemBooksBinding):
            RecyclerView.ViewHolder(binding.root), View.OnClickListener {
                fun bind(anchorBooksEntity: AnchorBooksEntity) {
                 Glide.with(binding.ivImageLink).load(anchorBooksEntity.imageLink)
                     .into(binding.ivImageLink)
                    if (anchorBooksEntity.fav) {
                        binding.ivFav.setColorFilter(Color.BLUE)
                    }
                    else {
                        binding.ivFav.setColorFilter(Color.BLACK)
                    }
                    binding.tvTitle.text = anchorBooksEntity.title

                }

        override fun onClick(v: View?) {
            booksItem.value = listAdapterBooks[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksVH {
        return BooksVH(ItemBooksBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BooksVH, position: Int) {
        val booksDC = listAdapterBooks[position]
        holder.bind(booksDC)
    }

    override fun getItemCount(): Int = listAdapterBooks.size
}