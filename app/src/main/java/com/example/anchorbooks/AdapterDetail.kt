package com.example.anchorbooks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailVH {
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
                    Glide.with(binding.ivImageLink2).load(bookDetailEntity.imageLink)
                        .into(binding.ivImageLink2)
                    binding.sDelivery.isClickable = false
                    binding.sDelivery.isChecked = bookDetailEntity.delivery
                    binding.tvAuthor.text = bookDetailEntity.author
                    binding.tvCountry.text = bookDetailEntity.country
                    binding.tvLanguage.text = bookDetailEntity.language
                    binding.tvPages.text = bookDetailEntity.pages.toString()
                    binding.tvPrice.text = bookDetailEntity.price.toString()
                    binding.wvLink.loadUrl(bookDetailEntity.link)
                    binding.wvLink.webViewClient =  object : WebViewClient(){}
                    val setting: WebSettings = binding.wvLink.settings
                    setting.javaScriptEnabled = true
                    binding.wvLink.loadUrl(bookDetailEntity.link)
                    itemView.setOnClickListener(this)
                    }

        override fun onClick(v: View?) {
            itemDetail.value = listBookDetail[adapterPosition]
        }
    }

}