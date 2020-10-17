package com.childhealthcare.parent.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.childhealthcare.parent.AccountActivity
import com.childhealthcare.parent.databinding.FragmentDashboardBinding
import com.childhealthcare.parent.model.GridMenu
import com.childhealthcare.parent.ui.OnListItemClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val PROFILE_INDEX = 0
private const val CHILDREN_INDEX = 1
private const val QUERY_INDEX = 2

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private val mViewModel: DashboardViewModel by viewModel()

    private var logoutClickCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.logout.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                logoutClickCount++
                if (logoutClickCount == 1)
                    Toast.makeText(context, "Press again to logout", Toast.LENGTH_SHORT).show()
                else
                    logout()
                delay(2000)
                logoutClickCount = 0
            }
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = mViewModel
        binding.onItemClick = OnDashboardItemClick()
    }

    private fun logout() {
        mViewModel.logoutUser()
        val intent = Intent(context, AccountActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    inner class OnDashboardItemClick : OnListItemClickListener<GridMenu> {
        override fun onItemClick(item: GridMenu, pos: Int) {
            when (pos) {
                PROFILE_INDEX -> {
                    val action =
                        DashboardFragmentDirections.actionDestDashboardToDestProfile(mViewModel.user!!)
                    binding.root.findNavController().navigate(action)
                }
                CHILDREN_INDEX -> {
                    val action =
                        DashboardFragmentDirections.actionDestDashboardToDestChildrenList()
                    binding.root.findNavController().navigate(action)
                }
                QUERY_INDEX -> {
                    val action = DashboardFragmentDirections.actionDestDashboardToDestQueries()
                    binding.root.findNavController().navigate(action)
                }
            }
        }

    }

}