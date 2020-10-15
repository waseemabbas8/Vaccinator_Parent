package com.childhealthcare.parent.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.childhealthcare.parent.databinding.FragmentProfileBinding
import com.childhealthcare.parent.model.User

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.user = user

        return binding.root
    }

}