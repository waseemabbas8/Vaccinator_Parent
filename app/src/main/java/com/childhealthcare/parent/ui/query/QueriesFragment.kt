package com.childhealthcare.parent.ui.query

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.childhealthcare.parent.R
import com.childhealthcare.parent.databinding.FragmentQueriesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class QueriesFragment : Fragment() {

    private lateinit var binding: FragmentQueriesBinding
    private val mViewModel: QueriesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQueriesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.addNewQuery.setOnClickListener {
            val action = QueriesFragmentDirections.actionDestQueriesToDestAddQuery()
            binding.root.findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = mViewModel

        mViewModel.getQueriesList()
    }

}