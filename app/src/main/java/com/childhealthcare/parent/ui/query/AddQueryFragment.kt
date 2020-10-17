package com.childhealthcare.parent.ui.query

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.childhealthcare.parent.R
import com.childhealthcare.parent.data.RESPONSE_CODE_OK
import com.childhealthcare.parent.databinding.FragmentAddQueryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddQueryFragment : Fragment() {

    private lateinit var binding: FragmentAddQueryBinding
    private val mViewModel: AddQueryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddQueryBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = mViewModel

        mViewModel.generalResponse.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            if (it.code == RESPONSE_CODE_OK)
                activity?.onBackPressed()
        })
    }

}