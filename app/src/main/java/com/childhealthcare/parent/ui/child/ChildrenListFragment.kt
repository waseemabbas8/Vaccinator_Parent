package com.childhealthcare.parent.ui.child

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.childhealthcare.parent.databinding.FragmentChildrenListBinding
import com.childhealthcare.parent.model.Child
import com.childhealthcare.parent.ui.OnListItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class ChildrenListFragment : Fragment() {

    private lateinit var binding: FragmentChildrenListBinding
    private val mViewModel: ChildrenListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChildrenListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = mViewModel
        binding.onChildClick = OnChildClick()
    }

    inner class OnChildClick : OnListItemClickListener<Child> {
        override fun onItemClick(item: Child, pos: Int) {
//            val action = ChildrenListFragmentDirections.actionDestChildrenToDestChild(item.id)
//            binding.root.findNavController().navigate(action)
        }

    }

}