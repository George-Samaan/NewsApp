package com.route.newsapp.newsSources

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.route.newsapp.R
import com.route.newsapp.ViewMessage
import com.route.newsapp.api.model.sourcesResponse.Source
import com.route.newsapp.databinding.FragmentNewsSourcesBinding
import com.route.newsapp.newsFragment.NewsFragment

class NewsSourcesFragment : Fragment() {

    lateinit var viewBiniding: FragmentNewsSourcesBinding
    lateinit var viewModel: SourcesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SourcesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBiniding = FragmentNewsSourcesBinding.inflate(inflater, container, false)
        return viewBiniding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeLiveData()
        viewModel.getNewsSources()
    }

    fun observeLiveData() {
        viewModel.isLoadingVisible.observe(viewLifecycleOwner) {
            changeLoadingVisibility(it)
        }
        viewModel.message.observe(viewLifecycleOwner, {
            showError(it)
        })
        viewModel.sourcesLiveData.observe(viewLifecycleOwner, {
            showNewsSources(it)
        })
    }

    val newsFragment = NewsFragment()

    private fun initViews() {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_contanier, newsFragment)
            .commit()

    }

    fun changeLoadingVisibility(isLoadingVisible: Boolean) {
        viewBiniding.progressBar.isVisible = isLoadingVisible
    }


    private fun showNewsSources(sources: List<Source?>?) {
        viewBiniding.errorView.isVisible = false
        viewBiniding.progressBar.isVisible = false
        sources?.forEach { source ->
            val tab = viewBiniding.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source
            viewBiniding.tabLayout.addTab(tab)
        }
        viewBiniding.tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                newsFragment.changeSource(source)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                newsFragment.changeSource(source)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

        })
        viewBiniding.tabLayout.getTabAt(0)?.select()
    }

    private fun showError(message: ViewMessage) {
        viewBiniding.errorView.isVisible = true
        viewBiniding.errorMessage.text = message.message
        viewBiniding.tryAgain.text = message.posActionName
        viewBiniding.tryAgain.setOnClickListener {
            message.posAction?.invoke()
        }
    }
}