package com.childhealthcare.parent.ui.child

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.childhealthcare.parent.data.RESPONSE_CODE_ERROR
import com.childhealthcare.parent.databinding.FragmentChildBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ChildFragment : Fragment() {

    private lateinit var binding: FragmentChildBinding
    private val mViewModel: ChildViewModel by viewModel{
        parametersOf(childId)
    }

    private var childId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args = ChildFragmentArgs.fromBundle(it)
            childId = args.childId
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChildBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.rvVaccinations.isNestedScrollingEnabled = false

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = mViewModel

        mViewModel.generalResponse.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            if (it.code == RESPONSE_CODE_ERROR)
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        })

    }

}