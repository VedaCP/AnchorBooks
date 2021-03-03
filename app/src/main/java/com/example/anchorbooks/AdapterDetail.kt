package com.example.anchorbooks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anchorbooks.databinding.ItemDetailBinding

class AdapterDetail: RecyclerView.Adapter<AdapterDetail.DetailVH>() {

    private var listBookDetail = listOf<BookDetailEntity>()
    private val itemDetail = MutableLiveData<BookDetailEntity>()

    fun update(list: List<BookDetailEntity>) {
        listBookDetail = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDetail.DetailVH {
        return DetailVH(ItemDetailBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AdapterDetail.DetailVH, position: Int) {
        val mBookDetail = listBookDetail[position]
        holder.bind(mBookDetail)
    }

    override fun getItemCount(): Int = listBookDetail.size

    inner class DetailVH(private val binding: ItemDetailBinding):
            RecyclerView.ViewHolder(binding.root), View.OnClickListener{
                fun bind (bookDetailEntity: BookDetailEntity){
                    Glide.with(binding.ivImageLink2).load(bookDetailEntity.id)

                }

        override fun onClick(v: View?): Boolean {
            itemDetail.value = listBookDetail[adapterPosition]
        }
    }

}