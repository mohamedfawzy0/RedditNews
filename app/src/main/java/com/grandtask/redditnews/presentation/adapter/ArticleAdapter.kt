package com.grandtask.redditnews.presentation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grandtask.redditnews.Constants.EXTRA_ARTICLE
import com.grandtask.redditnews.R
import com.grandtask.redditnews.databinding.ItemArticleRowBinding
import com.grandtask.redditnews.presentation.model.Children
import com.grandtask.redditnews.presentation.model.DataItem
import com.grandtask.redditnews.presentation.ui.article_details.ArticleDetailsActivity


class ArticleAdapter(var context: Context, var articleList: MutableList<Children>) :
    RecyclerView.Adapter<ArticleAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemArticleRowBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_article_row, parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val article = articleList[position].data
        holder.bind(article)

    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    inner class MyHolder(private val binding: ItemArticleRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataItem) {
            binding.article = item
            Glide.with(binding.root.context).load(item.media?.oembed?.thumbnail_url)
                .into(binding.articleImage)

            itemView.setOnClickListener {
                val intent = Intent(context, ArticleDetailsActivity::class.java)
                intent.putExtra(EXTRA_ARTICLE, item)
                context.startActivity(intent)
            }
        }
    }
}