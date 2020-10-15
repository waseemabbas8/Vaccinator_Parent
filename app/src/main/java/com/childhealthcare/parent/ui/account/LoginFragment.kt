package com.childhealthcare.parent.ui.account

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.childhealthcare.parent.MainActivity
import com.childhealthcare.parent.R
import com.childhealthcare.parent.data.RESPONSE_CODE_OK
import com.childhealthcare.parent.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val mViewModel: AccountViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.signUp.setOnClickListener {
            val action = LoginFragmentDirections.actionDestLoginToDestSignUp()
            binding.root.findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = mViewModel

        mViewModel.setValidator(binding)

        mViewModel.generalResponse.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            if (it.code == RESPONSE_CODE_OK) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        })
    }

}