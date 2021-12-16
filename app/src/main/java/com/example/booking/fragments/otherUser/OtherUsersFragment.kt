package com.example.booking.fragments.otherUser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booking.R
import com.example.booking.databinding.FragmentHotelsListBinding
import com.example.booking.databinding.FragmentOtherUsersBinding
import com.example.booking.fragments.list.HotelListAdapter
import com.example.booking.viewmodels.HotelViewModel
import com.example.booking.viewmodels.OtherUsersViewModel
import java.util.*
import kotlin.concurrent.schedule

class OtherUsersFragment : Fragment() {
    private lateinit var mUserViewModel: OtherUsersViewModel
    private var _binding: FragmentOtherUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOtherUsersBinding.inflate(inflater, container, false)
        val adapter = OtherUsersListAdapter(emptyList())
        val recyclerView = binding.rvRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this)[OtherUsersViewModel::class.java]

        mUserViewModel.usersList.observe(viewLifecycleOwner, { user ->
            adapter.setData(user)
        })
        setHasOptionsMenu(true)
        return binding.root
    }


}

