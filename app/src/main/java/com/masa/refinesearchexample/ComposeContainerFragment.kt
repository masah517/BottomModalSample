package com.masa.refinesearchexample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.masa.refinesearchexample.presentation.RefineDialogContainerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeContainerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return RefineDialogContainerView(requireContext()).apply {
            modalDismissCallback = {
                parentFragmentManager.popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MyMsg", "OnDestroy")
    }
}