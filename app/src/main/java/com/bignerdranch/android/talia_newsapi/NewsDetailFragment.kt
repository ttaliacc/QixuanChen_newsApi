package com.bignerdranch.android.talia_newsapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bignerdranch.android.talia_newsapi.NewsDetailFragment
import com.bignerdranch.android.talia_newsapi.databinding.FragmentNewsDetailBinding
import com.bumptech.glide.Glide

class NewsDetailFragment : Fragment() {

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!
    private val args: NewsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            articleTitle.text = args.article.title
            articleContent.text = args.article.content
            articleAuthor.text = "Author: ${args.article.author}"
            articlePublishedAt.text = args.article.publishedAt

            args.article.urlToImage?.let {
                Glide.with(this@NewsDetailFragment)
                    .load(it)
                    .into(articleImage)
            }
        }
        binding.buttonHome.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}