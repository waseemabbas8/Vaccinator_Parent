package com.childhealthcare.parent.ui.child

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.childhealthcare.parent.databinding.FragmentVaccinationsBinding


class VaccinationsFragment(private val mVieWModel: ChildViewModel) : Fragment() {

    private lateinit var binding: FragmentVaccinationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVaccinationsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.viewModel = mVieWModel

        binding.rvVaccinations.isNestedScrollingEnabled = false

        return binding.root
    }

}