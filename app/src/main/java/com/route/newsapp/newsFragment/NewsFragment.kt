package com.route.newsapp.newsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.route.domain.model.Article
import com.route.domain.model.Source
import com.route.newsapp.ViewMessage
import com.route.newsapp.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var viewBinding: FragmentNewsBinding
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNewsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    var source: Source? = null
    fun changeSource(source: Source) {
        this.source = source
        viewModel.loadNews(source)
    }


    val adapter = NewsAdapter(null)

    private fun showNewsList(articles: List<Article?>?) {
        adapter.changeData(articles)
    }

    private fun showError(message: ViewMessage) {
        viewBinding.errorView.isVisible = true
        viewBinding.errorMessage.text = message.message
        viewBinding.tryAgain.text = message.posActionName
        viewBinding.tryAgain.setOnClickListener {
            message.posAction?.invoke()
        }
    }

    fun changeLoadingVisibility(isLoadingVisible: Boolean) {
        viewBinding.progressBar.isVisible = isLoadingVisible
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.message.observe(viewLifecycleOwner) { showError(it) }
        viewModel.isLoadingVisible.observe(viewLifecycleOwner) { changeLoadingVisibility(it) }
        viewModel.newsLiveData.observe(viewLifecycleOwner) { showNewsList(it) }
    }

    private fun initViews() {
        viewBinding.rvNews.adapter = adapter
    }
}