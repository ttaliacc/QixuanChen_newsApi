package com.bignerdranch.android.talia_newsapi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.talia_newsapi.databinding.FragmentNewsListBinding

class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsListViewModel by viewModels {
        MakeListView(NewsRepository(RetrofitInstance.newsApiService))
    }
    private lateinit var adapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categories = resources.getStringArray(R.array.category)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.categoriesSpinner.adapter = adapter

        binding.categoriesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val category = categories[position]
                viewModel.setCategory(category)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val recycleAdapter = NewsListAdapter(mutableListOf()) { article ->
            val action = NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment(article)
            findNavController().navigate(action)
        }
        binding.newsListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.newsListRecyclerView.adapter = recycleAdapter

        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            recycleAdapter.updateArticles(articles)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        private const val TAG = "NewsListFragment"
    }
}