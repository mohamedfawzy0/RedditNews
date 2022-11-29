package com.grandtask.redditnews.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.grandtask.redditnews.R
import com.grandtask.redditnews.databinding.ActivityMainBinding
import com.grandtask.redditnews.presentation.adapter.ArticleAdapter
import com.grandtask.redditnews.presentation.model.Children
import com.grandtask.redditnews.presentation.model.DataItem
import com.grandtask.redditnews.presentation.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    lateinit var binding: ActivityMainBinding


    private var articleList = mutableListOf<Children>()
    private val adapter: ArticleAdapter by lazy { ArticleAdapter(this, articleList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.toolbar.title = getString(R.string.app_name)
        binding.refresh.setOnRefreshListener {
            viewModel.getArticles()
        }

        initView()
    }

    private fun initView() {
        binding.recView.layoutManager = LinearLayoutManager(this)
        binding.recView.adapter = adapter
        viewModel.isLoading.observe(this) {
            binding.refresh.isRefreshing = it
        }
        viewModel.errorMessage.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_INDEFINITE).show()
        }

        viewModel.articleLiveData.observe(this) {
            it?.let {
                binding.cardNoData.isVisible = it.isEmpty()
                binding.recView.isVisible = it.isNotEmpty()
                if (it.isNotEmpty()) {
                    articleList.clear()
                    articleList.addAll(it)
                    adapter.notifyDataSetChanged()
                }
            }

        }
    }
}