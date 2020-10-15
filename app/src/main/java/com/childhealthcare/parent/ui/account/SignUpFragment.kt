package com.childhealthcare.parent.ui.account

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.childhealthcare.parent.MainActivity
import com.childhealthcare.parent.R
import com.childhealthcare.parent.data.RESPONSE_CODE_OK
import com.childhealthcare.parent.databinding.FragmentSignUpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val mViewModel: AccountViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.spUc.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mViewModel.ucId = mViewModel.unionCouncils.value?.get(position)?.id ?: 0
                mViewModel.getMohallas()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        binding.spMoh.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mViewModel.mohId = mViewModel.mohallas.value?.get(position)?.id ?: 0
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = mViewModel

        mViewModel.setValidator(binding)

        mViewModel.getAllUcs()

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