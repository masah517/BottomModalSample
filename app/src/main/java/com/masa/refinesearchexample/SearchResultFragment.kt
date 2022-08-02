package com.masa.refinesearchexample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.masa.refinesearchexample.databinding.FragmentSearchResultBinding
import com.masa.refinesearchexample.presentation.RefineDialogContainerView
import com.masa.refinesearchexample.presentation.viewmodel.SearchResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment : Fragment() {

    lateinit var binding: FragmentSearchResultBinding
    private val viewModel: SearchResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyMsg", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResultBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadItems()

        binding.addButton.setOnClickListener {
            parentFragmentManager.commit {
                replace<SearchResultFragment>(R.id.fragmentContainer)
            }
        }

        binding.refineButton.setOnClickListener {
            // 方法１　検索結果画面に覆いかぶさるようにモーダルのComposeをAddViewする
            binding.root.addView(
                RefineDialogContainerView(context = requireContext()).apply {
                    modalDismissCallback = { isSearchRequired ->
                        binding.root.removeView(this)

                        if (isSearchRequired) {
                            viewModel.search()
                        }
                    }
                    z = 20F
                }, 0
            )
        }
    }
}
