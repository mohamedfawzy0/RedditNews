package com.grandtask.redditnews.presentation.ui.article_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.grandtask.redditnews.Constants
import com.grandtask.redditnews.R
import com.grandtask.redditnews.databinding.ActivityArticleDetailsBinding
import com.grandtask.redditnews.presentation.model.DataItem

class ArticleDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleDetailsBinding

    private lateinit var article: DataItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_article_details)
        getDataFromIntent()
        initView()
    }

    private fun getDataFromIntent() {
        if (intent!=null){
            article= intent.getParcelableExtra(Constants.EXTRA_ARTICLE)!!
        }
    }

    private fun initView() {
        binding.toolbar.llBack.visibility=View.VISIBLE
        binding.toolbar.title=article.title
        binding.toolbar.llBack.setOnClickListener { onBackPressed() }
        Glide.with(this).load(article.thumbnail).into(binding.articleImg)
        binding.tvBody.text=article.selftext
    }
}