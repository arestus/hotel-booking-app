package com.example.booking.fragments.loginHistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booking.R
import com.example.booking.databinding.FragmentLoginHistoryBinding
import com.example.booking.databinding.FragmentOtherUsersBinding
import com.example.booking.fragments.otherUser.OtherUsersListAdapter
import com.example.booking.viewmodels.LoginHistoryViewModel
import com.example.booking.viewmodels.OtherUsersViewModel



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
        setHasOptionsMenu(true)
        return binding.root
    }


}

