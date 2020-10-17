package com.childhealthcare.parent.ui.child

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.childhealthcare.parent.databinding.FragmentPolioBinding

class PolioFragment(private val mVieWModel: ChildViewModel) : Fragment() {

    private lateinit var binding: FragmentPolioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPolioBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.viewModel = mVieWModel

        return binding.root
    }

}