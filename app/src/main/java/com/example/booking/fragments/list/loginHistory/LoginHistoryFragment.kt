package com.example.booking.fragments.list.loginHistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booking.R
import com.example.booking.databinding.FragmentLoginHistoryBinding
import com.example.booking.viewmodels.LoginHistoryViewModel


class LoginHistoryFragment : Fragment() {
    private lateinit var mUserViewModel: LoginHistoryViewModel
    private var _binding: FragmentLoginHistoryBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginHistoryBinding.inflate(inflater, container, false)
        var adapter = LoginHistoryListAdapter(emptyList())
        val recyclerView = binding.rvRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this)[LoginHistoryViewModel::class.java]

        mUserViewModel.historyList.observe(viewLifecycleOwner, { user ->
            adapter.setData(user)
        })
        binding.loginHistoryBack.setOnClickListener{
            findNavController().navigate(R.id.action_loginHistoryFragment_to_profileFragment)
        }
        binding.loginHistoryTitle.text = "All Your Options"
        setHasOptionsMenu(true)
        return binding.root
    }


}

